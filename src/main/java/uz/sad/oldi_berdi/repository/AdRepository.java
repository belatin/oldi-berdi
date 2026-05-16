package uz.sad.oldi_berdi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sad.oldi_berdi.entity.Ad;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    Page<Ad> findAllByDeletedFalse(Pageable pageable);
    Optional<Ad> findByIdAndDeletedFalse(Long id);

    long countByDeletedFalse();
}