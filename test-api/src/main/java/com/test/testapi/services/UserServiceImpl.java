package com.test.testapi.services;

import com.test.testapi.domain.User;
import com.test.testapi.exceptions.EtAuthException;
import com.test.testapi.repositories.UserRepository;
import com.test.testapi.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public User validateUser(String email, String password) throws EtAuthException{
        return null;
    };
    @Override
    public User registerUser(String firstName, String lastName, String email, String password)  throws EtAuthException{
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if(count>0)
            throw new EtAuthException("Email already in use");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    };
}
