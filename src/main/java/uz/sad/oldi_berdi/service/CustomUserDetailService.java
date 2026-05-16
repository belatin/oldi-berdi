package uz.sad.oldi_berdi.service;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.sad.oldi_berdi.entity.User;
import uz.sad.oldi_berdi.entity.enums.UserStatus;
import uz.sad.oldi_berdi.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userByLogin = repository.findByPhoneAndDeletedFalse(login).orElseThrow(
                () -> new RuntimeException("User not found " + login)
        );

        if (userByLogin.getStatus() != UserStatus.ACTIVE) {
            // Bu xatolik Spring Security tomonidan ushlanadi va login sahifasiga qaytariladi
            throw new DisabledException("Hisobingiz bloklangan!");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(userByLogin.getPhone())
                .password(userByLogin.getPassword())
                .roles(userByLogin.getRole().name())
                .build();
    }
}
