package ptit.example.btlwebbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Address extends AbstractEntity {
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private String wardId;
    @Builder.Default
    private boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
