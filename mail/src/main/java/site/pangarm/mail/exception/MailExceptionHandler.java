package site.pangarm.mail.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static site.pangarm.mail.exception.ErrorCode.API_ERROR_INPUT_INVALID_VALUE;

@ControllerAdvice
public class MailExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleRuntimeException(MailException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response =
                ErrorResponse.builder()
                        .errorMessage(errorCode.getMessage())
                        .businessCode(errorCode.getCode())
                        .build();
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(API_ERROR_INPUT_INVALID_VALUE, e.getBindingResult());
        return ResponseEntity.status(API_ERROR_INPUT_INVALID_VALUE.getStatus()).body(response);
    }

}
