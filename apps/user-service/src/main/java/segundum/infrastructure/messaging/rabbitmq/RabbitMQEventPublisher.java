package segundum.infrastructure.messaging.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import segundum.domain.events.DomainEvent;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.infrastructure.messaging.messages.UserMessageMapper;
import segundum.infrastructure.utils.PropertiesReader;

/**
 * RabbitMQEventPublisher is an implementation of the DomainEventPublisher interface that publishes domain events to a RabbitMQ message broker.
 * It reads configuration properties from a "config.properties" file using a PropertiesReader instance.
 */
public class RabbitMQEventPublisher implements DomainEventPublisher {
	
	/**
	 * A static PropertiesReader instance to read configuration properties from the "config.properties" file.
	 * This instance is initialized in a static block, and if any error occurs during initialization,
	 * an ExceptionInInitializerError is thrown.
	 */
	private static final PropertiesReader reader; 

	/**
	 * Static block to initialize the PropertiesReader instance.
	 * If the "config.properties" file cannot be loaded, an ExceptionInInitializerError is thrown.
	 */
	static { 
		try { 
			reader = new PropertiesReader("config.properties"); 
		}
		catch (Exception e) { 
			throw new ExceptionInInitializerError("The file config.properties cannot be loaded: " + e.getMessage()); 
		} 
	}
	
	/**
	 * The message mapper for converting domain events to messages.
	 */
	private final UserMessageMapper mapper;

	/**
	 * Constructs a new RabbitMQEventPublisher by creating a connection to the RabbitMQ server and declaring the exchange.
	 * If any error occurs during initialization, a RabbitMQException is thrown.
	 *
	 * @param mapper the message mapper for converting domain events to messages
	 * @throws RabbitMQException if an error occurs during initialization
	 */
	public RabbitMQEventPublisher(UserMessageMapper mapper) {
		this.mapper = mapper;
		try {
			String uri = reader.getProperty("rabbitmq.uri");
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			String exchangeName = "bus";
			boolean durable = true;
			channel.exchangeDeclare(exchangeName, "topic", durable);
			channel.close();
			connection.close();
			}
			catch(Exception e) {
				throw new RabbitMQException("Error initializing RabbitMQ event publisher: " + e.getMessage());
			}		
	}

	@Override
	public <T extends DomainEvent> void publish(T event) {
		try {
			String uri = reader.getProperty("rabbitmq.uri"); 
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			Gson gson = new Gson();
			String message = gson.toJson(mapper.map(event));
			channel.basicPublish("bus", "bus.users." + event.getType(),
					new AMQP.BasicProperties.Builder().contentType("application/json").build(),
					message.getBytes());
			channel.close();
			connection.close();
			}
			catch(Exception e) {
				throw new RabbitMQException("Error publishing event to RabbitMQ: " + e.getMessage());
			}
	}
	
	

}