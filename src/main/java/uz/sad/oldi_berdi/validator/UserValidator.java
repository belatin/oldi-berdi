package uz.sad.oldi_berdi.validator;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.repository.UserRepository;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkByLogin(String login){
        return userRepository.findByPhoneAndDeletedFalse(login)
                .orElseThrow(() -> new CustomException("User by login = " + login + " not found"));
    }

    public User checkById(Long id){
        return userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new CustomException("User by id = " + id + " not found"));
    }

}
