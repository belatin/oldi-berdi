package uz.sad.oldi_berdi.mapper;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.Category;
import uz.sad.oldi_berdi.entity.dto.CategoryDto;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        if (category.getParent() != null){
            dto.setParentId(category.getParent().getId());
            dto.setParentName(category.getParent().getName());
        }
        return dto;
    }
}
