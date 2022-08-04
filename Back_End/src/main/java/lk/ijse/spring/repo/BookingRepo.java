package lk.ijse.spring.repo;

import lk.ijse.spring.dto.AdminDto;
import lk.ijse.spring.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, String> {

    @Query(value = "SELECT * FROM booking where customerNic=:customerNic order by bookingId desc", nativeQuery = true)
    List<Booking> getBookingsByCustomer(@Param("customerNic") String customerNic);

}
