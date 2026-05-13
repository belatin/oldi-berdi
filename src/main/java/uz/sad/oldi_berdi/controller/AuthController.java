package uz.sad.oldi_berdi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.sad.oldi_berdi.entity.dto.UserRegisterDto;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registerPage(){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserRegisterDto dto,
                           RedirectAttributes redirectAttributes){
        try {
            service.register(dto);
            return "redirect:/auth/login";
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/registration";
        }
    }

}
