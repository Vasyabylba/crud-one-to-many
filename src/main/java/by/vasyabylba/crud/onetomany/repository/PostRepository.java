package by.vasyabylba.crud.onetomany.repository;

import by.vasyabylba.crud.onetomany.model.Post;
import by.vasyabylba.crud.onetomany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query("delete from Post p where p.user = :user")
    void deleteAllByUser(@Param("user") User user);

    List<Post> findByUser(User user);
}