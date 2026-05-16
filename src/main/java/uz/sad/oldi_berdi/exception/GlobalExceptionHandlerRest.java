package uz.sad.oldi_berdi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.sad.oldi_berdi.entity.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandlerRest {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handlerException(BadRequestException e){
        ErrorDto errorDto = ErrorDto.builder()
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(
                errorDto,
                HttpStatus.BAD_REQUEST
        );
    }
}
