package lk.ijse.spring.repo;

import lk.ijse.spring.entity.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingPaymentRepo extends JpaRepository<BookingPayment,String> {
}
