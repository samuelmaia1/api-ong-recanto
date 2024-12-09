package com.tiapn.Recanto_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiapn.Recanto_API.classes.Animal;
import com.tiapn.Recanto_API.classes.User;

public interface UserRepository extends JpaRepository<User, String> {

}
