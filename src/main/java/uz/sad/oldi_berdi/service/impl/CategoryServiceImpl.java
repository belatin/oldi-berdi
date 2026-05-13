package uz.sad.oldi_berdi.service.impl;

import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.Category;
import uz.sad.oldi_berdi.entity.dto.CategoryDto;
import uz.sad.oldi_berdi.mapper.CategoryMapper;
import uz.sad.oldi_berdi.repository.CategoryRepository;
import uz.sad.oldi_berdi.service.CategoryService;
import uz.sad.oldi_berdi.validator.CategoryValidator;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidator categoryValidator;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, CategoryValidator categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryValidator = categoryValidator;
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAllByDeletedFalse().stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryValidator.checkById(id));
    }

    @Override
    public List<CategoryDto> getRootCategories() {
        List<Category> roots = categoryRepository.findAllByParentNull();
        return roots.stream().map(
                category -> {
                    CategoryDto dto = categoryMapper.toDto(category);
                    boolean hasChildren = categoryRepository.existsByParentId(category.getId());
                    dto.setHasChildren(hasChildren);
                    return dto;
                }).toList();
    }

    @Override
    public List<CategoryDto> getChildCategories(Long parentId) {
        List<Category> children = categoryRepository.findAllByParentId(parentId);
        return children.stream().map(
                category -> {
                    CategoryDto dto = categoryMapper.toDto(category);
                    boolean hasChildren = categoryRepository.existsByParentId(category.getId());
                    dto.setHasChildren(hasChildren);
                    return dto;
                }).toList();
    }
}
