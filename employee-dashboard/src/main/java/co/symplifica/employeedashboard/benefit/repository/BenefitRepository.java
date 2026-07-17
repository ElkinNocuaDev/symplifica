package co.symplifica.employeedashboard.benefit.repository;

import co.symplifica.employeedashboard.benefit.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    List<Benefit> findByEmployeeId(Long employeeId);

}