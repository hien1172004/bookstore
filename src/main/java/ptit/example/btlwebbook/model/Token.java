package ptit.example.btlwebbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "tbl_token")
public class Token extends AbstractEntity{
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "verificationToken")
    private String verificationToken;
}
