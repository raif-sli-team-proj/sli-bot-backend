package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.core.entity.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
