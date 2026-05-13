package uz.sad.oldi_berdi.validator;

import org.springframework.stereotype.Component;
import uz.sad.oldi_berdi.entity.Category;
import uz.sad.oldi_berdi.exception.CustomException;
import uz.sad.oldi_berdi.repository.CategoryRepository;

@Component
public class CategoryValidator {

    private final CategoryRepository repository;

    public CategoryValidator(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category checkById(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new CustomException("Category by id = " + id + " not found"));
    }
}
