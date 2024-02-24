package ru.hse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.core.service.AdminService;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("{userId}")
    public Boolean checkIfUserIsAdmin(@PathVariable("userId") String userId) {
        return adminService.checkIfUserIsAdmin(userId);
    }
}
