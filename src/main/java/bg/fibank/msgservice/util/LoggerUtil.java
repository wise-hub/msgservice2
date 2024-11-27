package bg.fibank.msgservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * A utility for standardized logging across the application.
 */
@Component
public class LoggerUtil {

    /**
     * Logs an informational message.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log.
     */
    public void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    /**
     * Logs an informational message with parameters.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log (with placeholders).
     * @param params  The parameters to include in the message.
     */
    public void info(Class<?> clazz, String message, Object... params) {
        getLogger(clazz).info(message, params);
    }

    /**
     * Logs a debug message.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log.
     */
    public void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    /**
     * Logs a debug message with parameters.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log (with placeholders).
     * @param params  The parameters to include in the message.
     */
    public void debug(Class<?> clazz, String message, Object... params) {
        getLogger(clazz).debug(message, params);
    }

    /**
     * Logs a warning message.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log.
     */
    public void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    /**
     * Logs a warning message with parameters.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log (with placeholders).
     * @param params  The parameters to include in the message.
     */
    public void warn(Class<?> clazz, String message, Object... params) {
        getLogger(clazz).warn(message, params);
    }

    /**
     * Logs an error message.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log.
     */
    public void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    /**
     * Logs an error message with parameters.
     *
     * @param clazz   The class where the log originates.
     * @param message The message to log (with placeholders).
     * @param params  The parameters to include in the message.
     */
    public void error(Class<?> clazz, String message, Object... params) {
        getLogger(clazz).error(message, params);
    }

    /**
     * Fetches a logger instance for the specified class.
     *
     * @param clazz The class for which the logger is required.
     * @return The logger instance.
     */
    private Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
