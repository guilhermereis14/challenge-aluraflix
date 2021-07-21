package br.com.aluraflix.infraestructure.exception;

import br.com.aluraflix.infraestructure.util.FormatterUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment env;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        log.error("M=exceptionHandler", ex);
        String message = "Falha interna no sistema. Se persistir o erro procurar o administrador";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList(message)));
    }

    @ExceptionHandler({JsonParseException.class, JsonMappingException.class, InvalidFormatException.class})
    public ResponseEntity<?> exceptionHandlerJSON(Exception ex) {
        log.error("M=exceptionHandlerJSON", ex);
        String message = "Payload Inválido";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.BAD_REQUEST, Arrays.asList(message)));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> exceptionHandlerNoContent(ServiceException ex) {
        log.error("M=exceptionHandlerNoContent", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessages()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> exceptionHandlerNoSuchElement(NoSuchElementException ex) {
        log.error("M=exceptionHandlerNoSuchElement", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage())));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> exceptionHandlerForbidden(ForbiddenException ex) {
        log.error("M=exceptionHandlerForbidden", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.FORBIDDEN, Arrays.asList(ex.getMessage())));
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> constraintViolationExceptionHandle(MethodArgumentNotValidException ex) {
        log.error("M=constraintViolationExceptionHandle", ex);
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(getErrorResponse(HttpStatus.BAD_REQUEST, errors));
    }

    private ErrorResponse getErrorResponse(HttpStatus status, List<String> errors) {
        return ErrorResponse.builder()
                .timestamp(FormatterUtil.localDateToString(LocalDateTime.now(), FormatterUtil.DDMMYYYY_HHmmss))
                .code(status.value())
                .messages(errors)
                .build();
    }

}
