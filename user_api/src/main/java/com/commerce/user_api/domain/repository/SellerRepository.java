package com.commerce.user_api.domain.repository;

import com.commerce.user_api.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository  extends JpaRepository<Seller, Long> {
    Optional<Seller> findByIdAndEmail(Long id, String Email);
    Optional<Seller> findByEmailAndPasswordAndVerifyIsTrue(String email, String password);
    Optional<Seller> findByEmail(String email);
}
