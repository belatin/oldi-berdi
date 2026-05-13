package uz.sad.oldi_berdi.mapper;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.CategoryAttribute;
import uz.sad.oldi_berdi.entity.CategoryAttributeOption;
import uz.sad.oldi_berdi.entity.dto.CategoryAttributeDto;

@Component
public class CategoryAttributeMapper {

    public CategoryAttributeDto toDto(CategoryAttribute attribute) {
        CategoryAttributeDto dto = new CategoryAttributeDto();
        dto.setId(attribute.getId());
        dto.setName(attribute.getName());
        dto.setAttributeType(attribute.getAttributeType().name());
        dto.setRequired(attribute.isRequired());
        dto.setOptions(
                attribute.getOptions().stream()
                .map(CategoryAttributeOption::getValue)
                        .toList()
        );
        return dto;
    }
}
