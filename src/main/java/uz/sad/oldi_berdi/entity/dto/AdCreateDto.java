package uz.sad.oldi_berdi.entity.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdCreateDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long locationId;

    @NotEmpty
    private List<MultipartFile> images;

    private Integer mainImageIndex;

    private Map<Long, String> attributeValues;
}
