package segundum.infrastructure.messaging.rabbitmq;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import segundum.application.notifications.ratings.RatedUserRole;
import segundum.application.notifications.ratings.RatingCreatedNotification;
import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.application.outbound.LogEmitter;
import segundum.infrastructure.facades.RatingNotificationFacade;
import segundum.infrastructure.facades.SaleNotificationFacade;
import segundum.infrastructure.utils.PropertiesReader;

/**
 * Represents a consumer that listens for events on the RabbitMQ bus.
 */
public class RabbitMQConsumer {

	/**
	 * The facade for the sales bounded context event handlers.
	 */
	private final SaleNotificationFacade saleNotificationFacade;

	/**
	 * The facade for the ratings bounded context event handlers.
	 */
	private final RatingNotificationFacade ratingNotificationFacade;

	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * The RabbitMQ connection.
	 */
	private Connection connection;

	/**
	 * The RabbitMQ channel.
	 */
	private Channel channel;

	/**
	 * The reader for loading configuration properties.
	 */
	private static final PropertiesReader reader;

	static {
		try {
			reader = new PropertiesReader("config.properties");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError("The file config.properties cannot be loaded: " + e.getMessage());
		}
	}

	/**
	 * Constructs a new RabbitMQConsumer with the given notification facades.
	 *
	 * @param saleNotificationFacade the facade for the sales bounded context event handlers
	 * @param ratingNotificationFacade the facade for the ratings bounded context event handlers
	 * @param logEmitter the log emitter
	 */
	public RabbitMQConsumer(SaleNotificationFacade saleNotificationFacade,
			RatingNotificationFacade ratingNotificationFacade, LogEmitter logEmitter) {
		this.saleNotificationFacade = saleNotificationFacade;
		this.ratingNotificationFacade = ratingNotificationFacade;
		this.logEmitter = logEmitter;
	}

	/**
	 * Starts consuming sales and ratings events from the RabbitMQ bus.
	 */
	public void start() {
		String uri = reader.getProperty("rabbitmq.uri");
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);
			connection = factory.newConnection();
			channel = connection.createChannel();
			String exchangeName = "bus";
			boolean durable = true;
			channel.exchangeDeclare(exchangeName, "topic", durable);
			final String queueName = "users";
			final String salesBindingKey = "bus.sales.#";
			final String ratingsBindingKey = "bus.ratings.#";
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String, Object> properties = null;
			channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);
			channel.queueBind(queueName, exchangeName, salesBindingKey);
			channel.queueBind(queueName, exchangeName, ratingsBindingKey);
			boolean autoAck = false;
			channel.basicConsume(queueName, autoAck, "user-consumer",
					new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag, Envelope envelope,
								AMQP.BasicProperties properties, byte[] body) throws IOException {
							long deliveryTag = envelope.getDeliveryTag();
							String content = new String(body);
							JsonObject object = JsonParser.parseString(content).getAsJsonObject();
							if (object.has("type") && "SaleCompleted".equals(object.get("type").getAsString())) {
								UUID purchaserId = UUID.fromString(object.get("purchaserId").getAsString());
								UUID sellerId = UUID.fromString(object.get("sellerId").getAsString());
								SaleCompletedNotification event = new SaleCompletedNotification(purchaserId, sellerId);
								saleNotificationFacade.onSaleCompletedNotification(event);
							}
							else if (object.has("type") && "RatingCreated".equals(object.get("type").getAsString())) {
								handleRatingCreated(object);
							}
							channel.basicAck(deliveryTag, false);
						}
					});
		}
		catch (Exception e) {
			throw new RabbitMQException("Error initializing RabbitMQ event consumer: " + e.getMessage());
		}
	}

	/**
	 * Parses a rating created event and delegates its handling to the rating
	 * notification facade. Events with an unknown role of the rated user are
	 * discarded with a warning.
	 *
	 * @param object the JSON payload of the rating created event
	 */
	private void handleRatingCreated(JsonObject object) {
		String roleValue = object.get("ratedUserRole").getAsString();
		RatedUserRole ratedUserRole;
		if ("Purchaser".equals(roleValue)) {
			ratedUserRole = RatedUserRole.PURCHASER;
		}
		else if ("Seller".equals(roleValue)) {
			ratedUserRole = RatedUserRole.SELLER;
		}
		else {
			logEmitter.warn("RatingCreated event discarded: unknown rated user role " + roleValue);
			return;
		}
		UUID ratedUserId = UUID.fromString(object.get("ratedUserId").getAsString());
		int score = object.get("score").getAsInt();
		RatingCreatedNotification event = new RatingCreatedNotification(ratedUserId, ratedUserRole, score);
		ratingNotificationFacade.onRatingCreatedNotification(event);
	}

	/**
	 * Stops consuming events by closing the channel and the connection.
	 */
	public void stop() {
		try {
			if (this.channel != null) {
				this.channel.close();
			}
			if (this.connection != null) {
				this.connection.close();
			}
		}
		catch (Exception e) {
			throw new RabbitMQException("Error closing resources in RabbitMQ: " + e.getMessage());
		}
	}

}
