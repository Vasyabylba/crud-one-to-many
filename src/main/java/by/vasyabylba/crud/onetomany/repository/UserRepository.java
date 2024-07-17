package by.vasyabylba.crud.onetomany.repository;

import by.vasyabylba.crud.onetomany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}