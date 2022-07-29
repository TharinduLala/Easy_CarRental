package lk.ijse.spring.controller;

import lk.ijse.spring.dto.AdminDto;
import lk.ijse.spring.service.AdminService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllAdmins() {
        return new ResponseUtil(200, "Ok", adminService.getAllAdmins());
    }

    @GetMapping(path = "/{adminNic}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchAdmin(@PathVariable String adminNic) {
        return new ResponseUtil(200, "Ok", adminService.searchAdmin(adminNic));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveAdmin(@ModelAttribute AdminDto adminDto) {
        adminService.saveAdmin(adminDto);
        return new ResponseUtil(200, "Saved Successfully..", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateAdmin(@RequestBody AdminDto adminDto) {
        adminService.updateAdmin(adminDto);
        return new ResponseUtil(200, "Updated Successfully..", null);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"adminNic"})
    public ResponseUtil deleteAdmin(@RequestParam String adminNic) {
        adminService.deleteAdmin(adminNic);
        return new ResponseUtil(200, "Deleted Successfully..", null);
    }

}
