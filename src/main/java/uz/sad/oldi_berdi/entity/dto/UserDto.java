package uz.sad.oldi_berdi.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String phone;
    private String status;
    private String created_at;
    private String updated_at;
}