package uz.sad.oldi_berdi.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.exception.BadRequestException;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.repository.UserRepository;

@Component
public class UserValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserValidator(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User checkByLogin(String login) {
        return userRepository.findByPhoneAndDeletedFalse(login)
                .orElseThrow(() -> new CustomException("User by login = " + login + " not found"));
    }


    public User checkById(Long id) {
        return userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new CustomException("User by id = " + id + " not found"));
    }

    public User validateNewPassword(String oldPassword, String newPassword, String login) {
        if (newPassword == null || newPassword.length() < 4) {
            throw new BadRequestException("Parol uzunligi kamida 4 da bolishi kerak");
        } else if (newPassword.length() > 60) {
            throw new BadRequestException("Parol uzunligi 60 dan kam bolishi kerak");
        } else {
            User user = checkByLogin(login);
            if (!encoder.matches(oldPassword, user.getPassword())) {
                throw new BadRequestException("Eski parol notog'ri");
            }
            return user;
        }
    }
}
