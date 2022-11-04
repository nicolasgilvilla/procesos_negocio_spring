package com.procces.business.app.repository;

import com.procces.business.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);

    List<User> findAllByLastName(String lastName);

    List<User> findAllByNameAndLastName(String name, String lastName);

    User findByEmail(String email);

}
