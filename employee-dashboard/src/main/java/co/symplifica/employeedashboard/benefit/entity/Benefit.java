package co.symplifica.employeedashboard.benefit.entity;

import co.symplifica.employeedashboard.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "benefits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String benefitName;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
