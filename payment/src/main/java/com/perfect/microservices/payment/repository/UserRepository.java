package com.perfect.microservices.payment.repository;

import com.perfect.microservices.payment.model.CaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CaUser, Integer> {

    Optional<CaUser> findByUserName(String userName);
}
