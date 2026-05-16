package uz.sad.oldi_berdi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerMvc {

    @ExceptionHandler({BadRequestException.class})
    public String handlerException(BadRequestException e, Model model, HttpServletRequest request){
        model.addAttribute("error", e.getMessage());
        return request.getServletPath();
    }
}
