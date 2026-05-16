package uz.sad.oldi_berdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.enums.UserStatus;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndDeletedFalse(String username);
    Optional<User> findByPhoneAndDeletedFalse(String login);
    Optional<User> findByIdAndDeletedFalse(Long id);
    long countByStatusAndDeletedFalse(UserStatus status);
    long countByDeletedFalse();

}
