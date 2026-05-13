package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.CategoryAttribute;

import java.util.List;

public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute, Long> {

    List<CategoryAttribute> findAllByCategoryIdAndDeletedFalse(Long categoryId);
}
