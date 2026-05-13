package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.ContactSettingsDto;
import uz.sad.oldi_berdi.entity.dto.UserDto;
import uz.sad.oldi_berdi.entity.dto.UserRegisterDto;

public interface UserService {
    void register(UserRegisterDto dto);
    UserDto getUser(String login);

    void editUsername(String newUsername, String login);

    void updateContactSettings(ContactSettingsDto dto, String login);

    ContactSettingsDto getContactSettings(String login);

    void updatePassword(String oldPassword, String newPassword, String login);

}
