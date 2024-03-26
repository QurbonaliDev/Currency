//package uz.devops.currency.web.rest.errors;
//
//import java.net.URI;
//import java.time.ZonedDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.http.HttpStatus;
//
//@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
//public class BadRequestAlertException {
//
//    private static final long serialVersionUID = 1L;
//
//    private final String entityName;
//    private final ErrorKey errorKey;
//    private final HttpStatus httpStatus;
//    private final ZonedDateTime timestamp;
//
//    public BadRequestAlertException(
//        String defaultMessage,
//        String entityName,
//        ErrorKey errorKey,
//        HttpStatus httpStatus,
//        ZonedDateTime timestamp
//    ) {
//        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey, httpStatus, timestamp);
//    }
//
//    public BadRequestAlertException(
//        URI type,
//        String defaultMessage,
//        String entityName,
//        ErrorKey errorKey,
//        HttpStatus httpStatus,
//        ZonedDateTime timestamp
//    ) {
//        super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey, httpStatus, timestamp));
//        this.entityName = entityName;
//        this.errorKey = errorKey;
//        this.httpStatus = httpStatus;
//        this.timestamp = timestamp;
//    }
//
//    private static Map<String, Object> getAlertParameters(
//        String entityName,
//        ErrorKey errorKey,
//        HttpStatus httpStatus,
//        ZonedDateTime timestamp
//    ) {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("message", "error." + errorKey);
//        parameters.put("params", entityName);
//        parameters.put("httpStatus", httpStatus);
//        parameters.put("timestamp", timestamp);
//        return parameters;
//    }
//
//    public String getEntityName() {
//        return entityName;
//    }
//
//    public ErrorKey getErrorKey() {
//        return errorKey;
//    }
//
//    public HttpStatus getHttpStatus() {
//        return httpStatus;
//    }
//
//    public ZonedDateTime getTimestamp() {
//        return timestamp;
//    }
//}
