package uz.sad.oldi_berdi.mapper;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.dto.ContactSettingsDto;
import uz.sad.oldi_berdi.entity.dto.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class UserMapper {

    public UserDto toDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .created_at(user.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm")))
                .updated_at(user.getUpdatedAt()
                        .format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm")))
                .build();
    }

    public ContactSettingsDto toContactSettingsDto(User user) {
        return ContactSettingsDto.builder()
                .allowPhoneCall(user.isAllowPhoneCall())
                .allowTelegram(user.isAllowTelegram())
                .build();
    }

    public void fromContactSettingsDto(ContactSettingsDto dto, User user) {
        user.setAllowPhoneCall(dto.isAllowPhoneCall());
        user.setAllowTelegram(dto.isAllowTelegram());
    }
}
