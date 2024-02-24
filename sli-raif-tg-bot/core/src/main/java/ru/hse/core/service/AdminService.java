package ru.hse.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.core.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Boolean checkIfUserIsAdmin(String userId) {
        return adminRepository.existsById(userId);
    }
}
