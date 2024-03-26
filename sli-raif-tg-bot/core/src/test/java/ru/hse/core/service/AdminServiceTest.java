package ru.hse.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hse.core.repository.AdminRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void whenUserIsAdmin_thenReturnsTrue() {
        String adminUserId = "adminUser";
        when(adminRepository.existsById(adminUserId)).thenReturn(true);

        Boolean isAdmin = adminService.checkIfUserIsAdmin(adminUserId);

        assertTrue(isAdmin);
    }

}