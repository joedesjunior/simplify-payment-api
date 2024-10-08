package com.picpay.simplify_payment_api.service.strategy;

import com.picpay.simplify_payment_api.dto.request.UserDtoRequest;
import com.picpay.simplify_payment_api.dto.response.UserDtoResponse;
import com.picpay.simplify_payment_api.mapper.UserMapper;
import com.picpay.simplify_payment_api.repository.UserRepository;
import com.picpay.simplify_payment_api.service.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class MerchantUserRegistrationStrategy implements UserRegistrationStrategy {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserValidator userValidator;

  public MerchantUserRegistrationStrategy(
      UserRepository userRepository, UserMapper userMapper, UserValidator userValidator) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.userValidator = userValidator;
  }

  @Override
  public UserDtoResponse register(UserDtoRequest userDtoRequest) {
    userValidator.validateUser(userDtoRequest);

    var user = userRepository.save(userMapper.toEntity(userDtoRequest));
    return userMapper.toUserDtoResponse(user);
  }
}
