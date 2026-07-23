package segundum.infrastructure.messaging.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RabbitMQ configuration for the product service.
 */
@Configuration
public class RabbitMQConfig {

	/**
	 * The exchange name.
	 */
	public static final String EXCHANGE = "bus";

	/**
	 * The queue name for all user messages.
	 */
	public static final String USERS_QUEUE = "product-service.users";

	/**
	 * The binding key pattern for all user messages.
	 */
	public static final String USERS_BK = "bus.users.*";

	/**
	 * The package to scan for message classes with @JsonSubTypes.
	 */
	private static final String MESSAGES_PACKAGE = "segundum.infrastructure.messaging.rabbitmq.messages";

	/**
	 * Creates the bus exchange.
	 *
	 * @return the topic exchange for the bus
	 */
	@Bean
	public TopicExchange busExchange() {
		return new TopicExchange(EXCHANGE);
	}

	/**
	 * Creates the queue for all user messages.
	 *
	 * @return the queue for all user messages
	 */
	@Bean
	public Queue userMessagesQueue() {
		return new Queue(USERS_QUEUE, true, false, false);
	}

	/**
	 * Creates the binding for all user messages.
	 *
	 * @return the binding connecting the user messages queue to the bus exchange
	 */
	@Bean
	public Binding userMessagesBinding() {
		return BindingBuilder.bind(userMessagesQueue())
				.to(busExchange()).with(USERS_BK);
	}

	/**
	 * Creates the JSON message converter with auto-discovery of message types.
	 * Scans @JsonSubTypes annotations at startup and builds a type map
	 * for polymorphic deserialization without a manual registry.
	 *
	 * @return the message converter
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Map<String, Class<?>> typeMap = discoverMessageTypes();
		return new MessageConverter() {
			@Override
			public Message toMessage(Object object, MessageProperties messageProperties) {
				throw new UnsupportedOperationException("Consumer-only converter");
			}
			@Override
			public Object fromMessage(Message message) {
				try {
					byte[] body = message.getBody();
					JsonNode node = mapper.readTree(body);
					String type = node.get("type").asText();
					Class<?> baseClass = typeMap.get(type);
					if (baseClass == null) {
						throw new IllegalArgumentException("Unknown message type: " + type);
					}
					return mapper.readValue(body, baseClass);
				} catch (IllegalArgumentException e) {
					throw e;
				} catch (Exception e) {
					throw new RuntimeException("Failed to deserialize message", e);
				}
			}
		};
	}

	/**
	 * Scans the messages package for classes annotated with @JsonSubTypes
	 * and builds a map from event type name to base class.
	 * Uses Spring's ResourcePatternResolver for fat JAR compatibility.
	 * Executes once at application startup.
	 *
	 * @return a map of event type names to base message classes
	 */
	private Map<String, Class<?>> discoverMessageTypes() {
		Map<String, Class<?>> map = new HashMap<>();
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			String pattern = "classpath*:" + MESSAGES_PACKAGE.replace('.', '/') + "/*.class";
			Resource[] resources = resolver.getResources(pattern);
			for (Resource resource : resources) {
				String fileName = resource.getFilename();
				if (fileName == null || !fileName.endsWith(".class")) {
					continue;
				}
				String className = MESSAGES_PACKAGE + "." + fileName.replace(".class", "");
				Class<?> clazz = Class.forName(className);
				JsonSubTypes subTypes = clazz.getAnnotation(JsonSubTypes.class);
				if (subTypes != null) {
					for (JsonSubTypes.Type type : subTypes.value()) {
						map.put(type.name(), clazz);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to discover message types", e);
		}

		return map;
	}

}
