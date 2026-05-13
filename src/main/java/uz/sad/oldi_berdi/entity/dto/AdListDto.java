package uz.sad.oldi_berdi.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdListDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String username;
    private String categoryName;
    private String locationName;
    private String createdAt;
    private String imageUrl;

}
