package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedFalse();
    Optional<Category> findByIdAndDeletedFalse(Long id);
    List<Category> findAllByParentNull();
    List<Category> findAllByParentId(Long parentId);
    boolean existsByParentId(Long parentId);

}
