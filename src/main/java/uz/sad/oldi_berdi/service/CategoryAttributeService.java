package uz.sad.oldi_berdi.service;

import uz.sad.oldi_berdi.entity.dto.CategoryAttributeDto;

import java.util.List;

public interface CategoryAttributeService {
    List<CategoryAttributeDto> getByCategoryId(Long categoryId);
}
