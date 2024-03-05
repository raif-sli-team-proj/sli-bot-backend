package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.core.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
