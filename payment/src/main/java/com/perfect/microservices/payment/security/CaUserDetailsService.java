package com.perfect.microservices.payment.security;

import com.perfect.microservices.payment.model.CaUser;
import com.perfect.microservices.payment.model.CaUserDetails;
import com.perfect.microservices.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaUserDetailsService implements UserDetailsService {


    @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<CaUser> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + "User not found"));
        return user.map(CaUserDetails::new).get();
    }
}
