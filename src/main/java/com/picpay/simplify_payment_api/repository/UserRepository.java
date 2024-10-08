package com.picpay.simplify_payment_api.repository;

import com.picpay.simplify_payment_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByDocumentNumber(String documentNumber);

  boolean existsByEmail(String email);
}
