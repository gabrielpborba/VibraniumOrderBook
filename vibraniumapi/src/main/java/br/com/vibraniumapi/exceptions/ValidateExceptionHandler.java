package br.com.vibraniumapi.exceptions;

import br.com.vibraniumapi.dto.ErrorMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidateExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public ValidateExceptionHandler(MessageSource messageSource){this.messageSource = messageSource;}


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDto>> handle(MethodArgumentNotValidException exception){
        System.out.println("MethodArgumentNotValidException exception is called");
        List<ErrorMessageDto> dto = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach( e -> {
            String message = messageSource.getMessage(e , LocaleContextHolder.getLocale());
            System.out.println("Error message: "+message);
            ErrorMessageDto error = new ErrorMessageDto(e.getField(), message);
            dto.add(error);
        });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
