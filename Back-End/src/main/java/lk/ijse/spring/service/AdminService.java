package lk.ijse.spring.service;

import lk.ijse.spring.dto.AdminDto;

import java.util.List;

public interface AdminService {
    void saveAdmin(AdminDto dto);

    void updateAdmin(AdminDto dto);

    void deleteAdmin(String adminNic);

    AdminDto searchAdmin(String adminNic);

    List<AdminDto> getAllAdmins();

    String getAdminPassword(String adminNic);
}
