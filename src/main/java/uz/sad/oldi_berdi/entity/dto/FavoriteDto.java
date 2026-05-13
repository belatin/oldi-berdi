package uz.sad.oldi_berdi.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteDto {

    private Long id;
    private String username;
    private String adTitle;
    private String created_at;
}
