package ptit.example.btlwebbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Null;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Voucher extends  AbstractEntity{
    @Column(columnDefinition = "DOUBLE default 0")
    private Double minimum; // Giá trị tối thiểu, mặc định 0
    @Column(nullable = false, unique = true)
    private String code; // Mã, không được để trống, duy nhất
    @Column(name = "name", nullable = false)
    private String name; // Tên mã giảm giá
    @Column(name = "value", nullable = false)
    private Double value; // Giá trị giảm
    private String createdBy;
    @Column(name = "start_date", nullable = false)
    private LocalDate start; // Thời gian bắt đầu

    @Column(name = "end_date",nullable = false)
    private LocalDate end; //

    @ManyToMany(mappedBy = "vouchers")
    private List<User> users;

    @OneToMany(mappedBy = "voucher")
    private List<Order> orders;
}
