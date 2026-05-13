package uz.sad.oldi_berdi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sad.oldi_berdi.entity.dto.ContactSettingsDto;
import uz.sad.oldi_berdi.service.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ProfileApiController {

    private final UserService userService;

    public ProfileApiController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/username")
    public ResponseEntity<?> editUsername(@RequestBody Map<String, String> body,
                                          Principal principal){
        String newUsername = body.get("username");
        if (newUsername == null || newUsername.trim().isEmpty())
            return ResponseEntity.badRequest().body("Username bosh bolishi mumkin emas");
        userService.editUsername(newUsername, principal.getName());
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/contact-settings")
    public ResponseEntity<?> updateContactSettings(@RequestBody ContactSettingsDto dto,
                                                   Principal principal){
        userService.updateContactSettings(dto, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contact-settings")
    public ResponseEntity<ContactSettingsDto> getContactSettings(Principal principal){
        ContactSettingsDto dto = userService.getContactSettings(principal.getName());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> body,
                                            Principal principal){
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");

        userService.updatePassword(oldPassword, newPassword, principal.getName());
        return ResponseEntity.ok().build();

    }
}