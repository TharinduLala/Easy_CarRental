package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.AdminDto;
import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.repo.AdminRepo;
import lk.ijse.spring.service.AdminService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private  AdminRepo adminRepo;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public void saveAdmin(AdminDto adminDto) {
        if (!adminRepo.existsById(adminDto.getAdminNic())) {
            adminRepo.save(modelMapper.map(adminDto, Admin.class));
        } else {
            throw new RuntimeException("Admin Already Exist...!");
        }
    }

    @Override
    public void updateAdmin(AdminDto adminDto) {
        if (adminRepo.existsById(adminDto.getAdminNic())) {
            adminRepo.save(modelMapper.map(adminDto, Admin.class));
        } else {
            throw new RuntimeException("No Such Admin To Update " + adminDto.getAdminNic() + ". Please Check the ID..!");
        }
    }

    @Override
    public void deleteAdmin(String adminNic) {
        if (adminRepo.existsById(adminNic)) {
            adminRepo.deleteById(adminNic);
        } else {
            throw new RuntimeException("No Admin for " + adminNic + ",Please Check the ID.");
        }
    }

    @Override
    public AdminDto searchAdmin(String adminNic) {
        if (adminRepo.existsById(adminNic)) {
            return modelMapper.map(adminRepo.findById(adminNic).get(), AdminDto.class);
        } else {
            throw new RuntimeException("No Admin for " + adminNic);
        }
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return modelMapper.map(adminRepo.findAll(), new TypeToken<List<AdminDto>>() {
        }.getType());
    }

    @Override
    public String getAdminPassword(String adminNic){
        if(adminRepo.existsById(adminNic)){
            return adminRepo.getAdminPassword(adminNic);
        }else {
            throw  new RuntimeException("Invalid User Id");
        }
    }
}
