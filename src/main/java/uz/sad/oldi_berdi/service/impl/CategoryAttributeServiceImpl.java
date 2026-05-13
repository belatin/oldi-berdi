package uz.sad.oldi_berdi.service.impl;

import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.dto.CategoryAttributeDto;
import uz.sad.oldi_berdi.mapper.CategoryAttributeMapper;
import uz.sad.oldi_berdi.repository.CategoryAttributeRepository;
import uz.sad.oldi_berdi.service.CategoryAttributeService;

import java.util.List;

@Service
public class CategoryAttributeServiceImpl implements CategoryAttributeService {

    private final CategoryAttributeRepository attributeRepository;
    private final CategoryAttributeMapper attributeMapper;

    public CategoryAttributeServiceImpl(CategoryAttributeRepository attributeRepository, CategoryAttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
    }

    @Override
    public List<CategoryAttributeDto> getByCategoryId(Long categoryId) {
        return attributeRepository.findAllByCategoryIdAndDeletedFalse(categoryId)
                .stream()
                .map(attributeMapper::toDto)
                .toList();
    }
}
