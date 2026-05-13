package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
