package com.rabilmiraliyev.bookstore.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rabilmiraliyev.bookstore.model.Users;
import com.rabilmiraliyev.bookstore.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserPricipal(user);
    }
}
