    package ptit.example.btlwebbook.model;

    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.SuperBuilder;

    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    public class Cart extends  AbstractEntity {

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CartItem> cartItems;


    }
