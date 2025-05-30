package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_id", columnList = "id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false, unique = true)
    private String email;

    private String service; // Google, Facebook
    private String serviceId; // userId Google || Facebook
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    // Nam: 0, Nữ: 1
    @Builder.Default
    private Integer gender = 0;

    private String birthday;

    private String phoneNumber;

    @Embedded
    private  Avatar avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Cart cart;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ROLE role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "user_voucher",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id")
    )
    private  List<Voucher> vouchers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

//    xac thuc quyen nguoi dung
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> "ROLE_" + role.name());
    }
//dung cai gi de login
    @Override
    public String getUsername() {
        return this.getEmail();
    }

// kiem tra tai khoan het han hay khong
    @Override
    public boolean isAccountNonExpired() {
        return true; // true  khong bao h het han
    }
// kiem tra tai khoan co bi khoa hay khong
    @Override
    public boolean isAccountNonLocked() {
        return this.status == UserStatus.ACTIVE;
    }
//    kiem tra password het han hay khong
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
// kiem tra user co dang kich hoat khong
    @Override
    public boolean isEnabled() {
        return this.status == UserStatus.ACTIVE;
    }
}
