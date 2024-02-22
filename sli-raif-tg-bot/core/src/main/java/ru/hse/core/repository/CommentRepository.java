package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.core.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
