package uz.sad.oldi_berdi.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryAttributeDto {

    private Long id;
    private String name;
    private String attributeType;
    private boolean required;
    private List<String> options;

}
