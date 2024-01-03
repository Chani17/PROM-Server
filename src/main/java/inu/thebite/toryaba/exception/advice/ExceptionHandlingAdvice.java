package inu.thebite.toryaba.exception.advice;


import inu.thebite.toryaba.exception.AccessDeniedException;
import inu.thebite.toryaba.exception.AuthenticationException;
import inu.thebite.toryaba.model.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * ExceptionController에서 발생시킨 오류를 Handling
 */
@RestControllerAdvice
public class ExceptionHandlingAdvice {

    // 인가 오류
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> handlingAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(e.getMessage()));
    }

    // 인증 오류
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ExceptionResponse> handlingAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getMessage()));
    }

}
