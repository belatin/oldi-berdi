package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.ContactSettingsDto;
import uz.sad.oldi_berdi.entity.dto.UserDto;
import uz.sad.oldi_berdi.entity.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    void register(UserRegisterDto dto);
    UserDto getUser(String login);

    void editUsername(String newUsername, String login);

    void updateContactSettings(ContactSettingsDto dto, String login);

    ContactSettingsDto getContactSettings(String login);

    void updatePassword(String oldPassword, String newPassword, String login);

    List<UserDto> getAll();

    UserDto getById(Long id);

    void blockUser(Long id);

    void unBlockUser(Long id);

    long countUsers();

    long countOfBlockedUsers();
}
