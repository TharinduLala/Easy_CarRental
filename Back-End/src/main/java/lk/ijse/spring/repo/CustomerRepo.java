package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<Customer, String> {

    @Query("SELECT c.customerPassword FROM Customer c WHERE c.customerNic=:customerNic")
    String getCustomerPassword(@Param("customerNic") String customerNic);

}
