package br.com.fsbr.processoapi.config.excep.advice;

import br.com.fsbr.processoapi.config.excep.model.ErrorResponse;
import br.com.fsbr.processoapi.config.excep.model.ProcessoJaCadastrado;
import br.com.fsbr.processoapi.config.excep.model.ProcessoNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(ProcessoJaCadastrado.class)
    public ResponseEntity<ErrorResponse> processoJaCadastradoException(HttpServletRequest request, Exception ex) {

        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProcessoNaoEncontrado.class)
    public ResponseEntity<ErrorResponse> processoNaoEncontradoException(HttpServletRequest request, Exception ex) {

        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
