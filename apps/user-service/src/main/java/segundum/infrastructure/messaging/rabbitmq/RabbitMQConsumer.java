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

import segundum.application.notifications.sales.SaleCompletedNotification;
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
	 * Constructs a new RabbitMQConsumer with the given sale notification facade.
	 *
	 * @param saleNotificationFacade the facade for the sales bounded context event handlers
	 */
	public RabbitMQConsumer(SaleNotificationFacade saleNotificationFacade) {
		this.saleNotificationFacade = saleNotificationFacade;
	}

	/**
	 * Starts consuming sales events from the RabbitMQ bus.
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
			boolean exclusive = false;
			boolean autodelete = false;
			Map<String, Object> properties = null;
			channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);
			channel.queueBind(queueName, exchangeName, salesBindingKey);
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
							channel.basicAck(deliveryTag, false);
						}
					});
		}
		catch (Exception e) {
			throw new RabbitMQException("Error initializing RabbitMQ event consumer: " + e.getMessage());
		}
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
