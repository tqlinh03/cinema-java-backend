package com.cinema.backend.handler;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cinema.backend.exception.ActivationTokenException;
import com.cinema.backend.exception.OperationNotPermittedException;

import java.util.HashSet;
import java.util.Set;

import static com.cinema.backend.handler.BusinessErrorCodes.ACCOUNT_DISABLED;
import static com.cinema.backend.handler.BusinessErrorCodes.ACCOUNT_LOCKED;
import static com.cinema.backend.handler.BusinessErrorCodes.BAD_CREDENTIALS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(ACCOUNT_LOCKED.getCode())
                                .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponseWrapper> handleException(DisabledException exp) {

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .businessErrorCode(ACCOUNT_DISABLED.getCode())
        .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
        .error(exp.getMessage())
        .build();

        ExceptionResponseWrapper responseWrapper = new ExceptionResponseWrapper(exceptionResponse);

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(responseWrapper);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponseWrapper> handleException() {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .businessErrorCode(BAD_CREDENTIALS.getCode())
        .businessErrorDescription(BAD_CREDENTIALS.getDescription())
        .error("Tên đăng nhập hoặc mật khẩu không chính xác")
        .build();

ExceptionResponseWrapper responseWrapper = new ExceptionResponseWrapper(exceptionResponse);

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(responseWrapper);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ActivationTokenException.class)
    public ResponseEntity<ExceptionResponse> handleException(ActivationTokenException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    //var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
//         exp.printStackTrace();
//         return ResponseEntity
//                 .status(INTERNAL_SERVER_ERROR)
//                 .body(
//                         ExceptionResponse.builder()
//                                 .businessErrorDescription("Lỗi nội bộ, vui lòng liên hệ với quản trị viên")
//                                 .error(exp.getMessage())
//                                 .build()
//                 );
//     }

         @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseWrapper> handleException(Exception exp) {
        exp.printStackTrace();
        
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .businessErrorDescription("Lỗi nội bộ, vui lòng liên hệ với quản trị viên")
                .error(exp.getMessage())
                .build();

        ExceptionResponseWrapper responseWrapper = new ExceptionResponseWrapper(exceptionResponse);

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(responseWrapper);
    }
}