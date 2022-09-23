package com.procces.business.app.repository;

import com.procces.business.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);

    List<User> findAllByLastName(String lastName);

    List<User> findAllByNameAndLastName(String name, String lastName);

}
