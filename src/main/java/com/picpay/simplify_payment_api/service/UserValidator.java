package com.picpay.simplify_payment_api.service;

import com.picpay.simplify_payment_api.dto.request.UserDtoRequest;
import com.picpay.simplify_payment_api.exception.UserAlreadyExistsException;
import com.picpay.simplify_payment_api.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
  private final UserRepository userRepository;

  public UserValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validateUser(UserDtoRequest userDtoRequest) {
    if (userExists(userDtoRequest.getDocumentNumber())) {
      throw new UserAlreadyExistsException("User with this CPF/CNPJ already exists.");
    }
    if (emailExists(userDtoRequest.getEmail())) {
      throw new UserAlreadyExistsException("Email already registered.");
    }
  }

  private boolean userExists(String documentNumber) {
    return userRepository.existsByDocumentNumber(documentNumber);
  }

  private boolean emailExists(String email) {
    return userRepository.existsByEmail(email);
  }
}
