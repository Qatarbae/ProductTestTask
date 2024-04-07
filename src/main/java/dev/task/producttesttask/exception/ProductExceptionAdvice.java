package dev.task.producttesttask.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//@ControllerAdvice
//public class ProductExceptionAdvice {
//
//    private final MessageSource messageSource;
//
//    public ProductExceptionAdvice(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ProblemDetail> exception(RuntimeException exception,
//                                                   Locale locale) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
//                        Objects.requireNonNull(this.messageSource.getMessage(
//                                exception.getMessage(),
//                                new Object[0],
//                                exception.getMessage(), locale))));
//    }
//
//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
//        ProblemDetail problemDetail = ProblemDetail
//                .forStatusAndDetail(HttpStatus.BAD_REQUEST,
//                        this.messageSource.getMessage("Bad request", new Object[0],
//                                "Bad request", locale));
//        problemDetail.setProperty("errors",
//                exception.getAllErrors().stream()
//                        .map(ObjectError::getDefaultMessage)
//                        .toList());
//
//        return ResponseEntity.badRequest()
//                .body(problemDetail);
//    }
//}

@RestControllerAdvice
public class ProductExceptionAdvice {

    private final MessageSource messageSource;

    public ProductExceptionAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception, Locale locale) {
        String errorMessage = getMessage("error.runtime", locale);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<String>> handleBindException(BindException exception, Locale locale) {
        List<String> errors = exception.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    private String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, new Object[0], key, locale);
    }
}
