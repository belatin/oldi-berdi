package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);

    List<CategoryDto> getRootCategories();

    List<CategoryDto> getChildCategories(Long parentId);
}
