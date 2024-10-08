package com.picpay.simplify_payment_api.entity;

import com.picpay.simplify_payment_api.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
    name = "user_account",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "documentNumber"),
      @UniqueConstraint(columnNames = "email")
    })
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;

  @Column(unique = true)
  private String documentNumber;

  @Column(unique = true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserType userType;
}
