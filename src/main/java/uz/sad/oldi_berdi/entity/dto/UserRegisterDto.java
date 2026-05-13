package uz.sad.oldi_berdi.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterDto {

    @NotBlank
    private String phone;

    @NotBlank
    @Size(min = 4)
    private String password;
}
