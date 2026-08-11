package segundum.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Represents the RabbitMQ configuration for the sale service.
 */
@Configuration
public class RabbitMQConfig {
	
	/**
	 * The name of the topic exchange.
	 */
	public static final String EXCHANGE_NAME = "bus";
	
	/**
	 * The routing key prefix for sale events.
	 */
	public static final String ROUTING_KEY = "bus.sales.";
	
	/**
	 * Creates the topic exchange bean.
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	/**
	 * Creates the JSON message converter bean.
	 *
	 * @return the message converter
	 */
	@Bean 
	MessageConverter jsonMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return new Jackson2JsonMessageConverter(objectMapper);
	}
	
	/**
	 * Creates the RabbitTemplate bean.
	 *
	 * @param connectionFactory the RabbitMQ connection factory
	 * @param converter         the message converter
	 * @return the RabbitTemplate
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter);
		return rabbitTemplate;
	}
	
}
