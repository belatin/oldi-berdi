package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByDeletedFalse();
    Optional<Location> findByIdAndDeletedFalse(Long id);
    List<Location> findAllByParentNull();
    List<Location> findAllByParentId(Long parentId);
    boolean existsByParentId(Long parentId);


}
