using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using RabbitMQ.Client;
using Segundum.Events;
using Segundum.Ports;

namespace Segundum.Adapters;

/// <summary>
/// Publishes the events of the ratings context to the topic exchange of the
/// application bus, following the publisher of the course tutorial.
/// </summary>
public class RabbitMQEventPublisher : IEventPublisher
{
    private readonly IConnection _connection;
    private readonly string _exchangeName;
    private readonly string _routingKey;

    /// <summary>
    /// The bus messages are JSON with camelCase names, like the ones already
    /// published by the Java microservices.
    /// </summary>
    private static readonly JsonSerializerOptions JsonOptions = new()
    {
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
        DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
    };

    public RabbitMQEventPublisher(IConnection connection, IConfiguration config)
    {
        _connection = connection;
        _exchangeName = config["RabbitMQ:Exchange"] ?? "bus";
        _routingKey = config["RabbitMQ:RoutingKey"] ?? "bus.ratings.";
    }

    public async Task PublishAsync(Event @event)
    {
        await using var channel = await _connection.CreateChannelAsync();

        await channel.ExchangeDeclareAsync(
                exchange: _exchangeName,
                type: ExchangeType.Topic,
                durable: true);

        var json = JsonSerializer.Serialize(@event, @event.GetType(), JsonOptions);
        var body = Encoding.UTF8.GetBytes(json);

        var rkey = _routingKey + @event.Type;

        var props = new BasicProperties
        {
            Persistent = true,
            ContentType = "application/json"
        };

        await channel.BasicPublishAsync(
                exchange: _exchangeName,
                routingKey: rkey,
                mandatory: false,
                basicProperties: props,
                body: body);
    }
}
