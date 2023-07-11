package com.test.testapi.services;

import com.test.testapi.domain.User;
import com.test.testapi.exceptions.EtAuthException;



public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password)  throws EtAuthException;
}
