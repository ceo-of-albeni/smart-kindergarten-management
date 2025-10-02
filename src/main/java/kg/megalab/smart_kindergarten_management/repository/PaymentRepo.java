package kg.megalab.smart_kindergarten_management.repository;

import kg.megalab.smart_kindergarten_management.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findAllByGroupChildrenIdAndPaymentDateBetween(Long id, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
