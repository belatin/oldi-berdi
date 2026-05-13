package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUserId(Long userId);
    Optional<Favorite> findByUserIdAndAdId(Long userId, Long adId);
    void deleteByUserIdAndAdId(Long userId, Long adId);
}
