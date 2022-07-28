package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepo extends JpaRepository<Admin, String> {
    @Query("SELECT a.adminPassword FROM Admin a WHERE a.adminNic=:adminNic")
    String getAdminPassword(@Param("adminNic") String adminNic);
}
