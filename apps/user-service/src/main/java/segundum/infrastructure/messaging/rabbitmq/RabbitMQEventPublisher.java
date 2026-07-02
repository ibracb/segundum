package segundum.infrastructure.messaging.rabbitmq;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.DomainEventPublisher;
import segundum.utils.readers.PropertiesReader;

public class RabbitMQEventPublisher implements DomainEventPublisher {
	
	private static final PropertiesReader reader; 

	static { 
		try { 
			reader = new PropertiesReader("config.properties"); 
		}
		catch (Exception e) { 
			throw new ExceptionInInitializerError("No se pudo cargar config.properties: " + e.getMessage()); 
		} 
	}
	
	public RabbitMQEventPublisher() {	
		try {
			String uri = reader.getProperty("rabbitmq.uri");
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			String exchangeName = "bus";
			boolean durable = true;
			channel.exchangeDeclare(exchangeName,"topic", durable);
			channel.close();
			connection.close();
			}
			catch(Exception e) {
				throw new RabbitMQException("Error initializing RabbitMQ publisher: " + e.getMessage());
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
			String message = gson.toJson(event);
			channel.basicPublish("bus", "bus.users." + event.getType().getSimpleName(), new AMQP.BasicProperties.Builder()
					.contentType("application/json")
					.deliveryMode(2)
					.build(), message.getBytes());
			channel.close();
			connection.close();
			}
			catch(Exception e) {
				throw new RabbitMQException("Error publishing event to RabbitMQ: " + e.getMessage());
			}
	}
	
	

}
