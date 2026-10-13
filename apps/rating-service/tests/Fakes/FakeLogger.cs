using Microsoft.Extensions.Logging;

namespace Segundum.Fakes;

public class FakeLogger<T> : ILogger<T>
{
    private readonly List<string> _errors = new();

    public IReadOnlyList<string> Errors => _errors;

    public IDisposable? BeginScope<TState>(TState state) where TState : notnull => null;

    public bool IsEnabled(LogLevel logLevel) => true;

    public void Log<TState>(LogLevel logLevel, EventId eventId, TState state,
            Exception? exception, Func<TState, Exception?, string> formatter)
    {
        if (logLevel >= LogLevel.Error)
            _errors.Add(formatter(state, exception));
    }
}
