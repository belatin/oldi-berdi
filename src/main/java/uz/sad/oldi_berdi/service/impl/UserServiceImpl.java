package uz.sad.oldi_berdi.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.ContactSettingsDto;
import uz.sad.oldi_berdi.entity.dto.UserDto;
import uz.sad.oldi_berdi.entity.dto.UserRegisterDto;
import uz.sad.oldi_berdi.entity.enums.Role;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.mapper.UserMapper;
import uz.sad.oldi_berdi.repository.UserRepository;
import uz.sad.oldi_berdi.service.UserService;
import uz.sad.oldi_berdi.validator.LoginValidator;
import uz.sad.oldi_berdi.validator.UserValidator;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final LoginValidator loginValidator;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, LoginValidator loginValidator, UserValidator userValidator, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.loginValidator = loginValidator;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    public void register(UserRegisterDto dto) {
        if (!loginValidator.isValid(dto.getPhone()))
            throw new CustomException("Telefon raqam notog'ri formatda");
        if (userRepository.findByPhoneAndDeletedFalse(dto.getPhone()).isPresent())
            throw new CustomException("Bu telefon raqam oldin royhattan o'tgan");
        User user = new User();
        user.setPhone(dto.getPhone().replace(" ", ""));
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(String phoneNum) {
        return  userMapper.toDto(userValidator.checkByLogin(phoneNum));
    }

    @Override
    public void editUsername(String newUsername, String phoneNum) {
        User user = userValidator.checkByLogin(phoneNum);
        user.setUsername(newUsername);
        userRepository.save(user);
    }

    @Override
    public void updateContactSettings(ContactSettingsDto dto, String phoneNum) {
        User user = userValidator.checkByLogin(phoneNum);
        userMapper.fromContactSettingsDto(dto, user);
        userRepository.save(user);
    }

    @Override
    public ContactSettingsDto getContactSettings(String login) {
        User user = userValidator.checkByLogin(login);
        return userMapper.toContactSettingsDto(user);
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String userPhone) {
        User user = userValidator.checkByLogin(userPhone);
        if (user.getPassword().equals(encoder.encode(oldPassword))){
            user.setPassword(encoder.encode(newPassword));
        }else {

        }
    }
}
