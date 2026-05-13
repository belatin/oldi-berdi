package uz.sad.oldi_berdi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sad.oldi_berdi.entity.base.BaseEntity;
import uz.sad.oldi_berdi.entity.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(length = 15)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "allow_phone_call", nullable = false)
    private boolean allowPhoneCall = true;

    @Column(name = "allow_telegram", nullable = false)
    private boolean allowTelegram = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();
}
