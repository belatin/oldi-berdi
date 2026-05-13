package uz.sad.oldi_berdi.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdDetailDto {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String status;
    private String username;
    private String sellerPhone;
    private boolean allowPhoneCall;
    private boolean allowTelegram;
    private String categoryName;
    private String locationName;
    private String createdAt;
    private List<String> imagesUrls;
    private Map<String, String> attributeCharacteristic;

}
