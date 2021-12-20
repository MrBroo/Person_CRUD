package byfayzullayev.person.repository;

import byfayzullayev.person.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLastname(String lastname);
}