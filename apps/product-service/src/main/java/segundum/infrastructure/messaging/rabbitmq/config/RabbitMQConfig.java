package segundum.infrastructure.messaging.rabbitmq.config;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
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
 * RabbitMQ configuration for the product service.
 */
@Configuration
public class RabbitMQConfig {

	/**
	 * The exchange name.
	 */
	public static final String EXCHANGE_NAME = "bus";
	
	public static final String QUEUE_NAME = "products";

	public static final String ROUTING_KEY = "bus.products.";

	/**
	 * The binding key pattern for all user messages.
	 */
	public static final String USERS_BINDING_KEY = "bus.users.#";

	/**
	 * The binding key pattern for all sale messages.
	 */
	public static final String SALES_BINDING_KEY = "bus.sales.#";
	
	@Bean
	public TopicExchange exchange () {
		return new TopicExchange(EXCHANGE_NAME);
	}
	 
	@Bean
	public Queue queue() {
		boolean durable = true;
		boolean exclusive = false;
		boolean autodelete = false;
		return new Queue(QUEUE_NAME, durable, exclusive, autodelete);
	}
	
	@Bean
	public Binding bindingUsers(Queue queue, Exchange exchange) {
		Map<String, Object> properties = null;
		return BindingBuilder.bind(queue).to(exchange).with(USERS_BINDING_KEY).and(properties);
	}
	  
	@Bean
	public Binding bindingSales(Queue queue, Exchange exchange) {
		Map<String, Object> properties = null;
		return BindingBuilder.bind(queue).to(exchange).with(SALES_BINDING_KEY).and(properties);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
			MessageConverter converter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(converter);
	    return rabbitTemplate;
	}

}
