package com.test.testapi.repositories;

import com.test.testapi.domain.User;
import com.test.testapi.exceptions.EtAuthException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository{
        //extends JpaRepository<User, Long>  {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
    User findByEmailAndPassword(String email, String password) throws EtAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer id);
}
