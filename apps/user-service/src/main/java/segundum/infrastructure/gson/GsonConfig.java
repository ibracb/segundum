package segundum.infrastructure.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Configures Gson for JSON serialization and deserialization.
 */
@Provider
public class GsonConfig implements ContextResolver<Gson> {

	/**
	 * The Gson instance used for JSON serialization and deserialization.
	 */
    private final Gson gson;

    /**
	 * Constructs a new GsonConfig instance and initializes the Gson instance with custom serializers and deserializers.
	 */
    public GsonConfig() {
        gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (src, type, ctx) ->
                    new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, type, ctx) ->
                    LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(Instant.class,
                    (JsonSerializer<Instant>) (src, type, ctx) ->
                        new JsonPrimitive(src.toString()))
            .registerTypeAdapter(Instant.class,
                    (JsonDeserializer<Instant>) (json, type, ctx) ->
                        Instant.parse(json.getAsString()))
            .create();
    }
    
    @Override
    public Gson getContext(Class<?> type) {
        return gson;
    }
    
}