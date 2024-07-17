package by.vasyabylba.crud.onetomany.repository;

import by.vasyabylba.crud.onetomany.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}