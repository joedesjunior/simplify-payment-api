package com.picpay.simplify_payment_api.service;

import com.picpay.simplify_payment_api.enums.UserType;
import com.picpay.simplify_payment_api.service.strategy.CommonUserRegistrationStrategy;
import com.picpay.simplify_payment_api.service.strategy.MerchantUserRegistrationStrategy;
import com.picpay.simplify_payment_api.service.strategy.UserRegistrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationFactory {
  private final CommonUserRegistrationStrategy commonUserRegistrationStrategy;
  private final MerchantUserRegistrationStrategy merchantUserRegistrationStrategy;

  public UserRegistrationFactory(
      CommonUserRegistrationStrategy commonUserRegistrationStrategy,
      MerchantUserRegistrationStrategy merchantUserRegistrationStrategy) {
    this.commonUserRegistrationStrategy = commonUserRegistrationStrategy;
    this.merchantUserRegistrationStrategy = merchantUserRegistrationStrategy;
  }

  public UserRegistrationStrategy getStrategy(UserType userType) {
    return switch (userType) {
      case COMMON -> commonUserRegistrationStrategy;
      case MERCHANT -> merchantUserRegistrationStrategy;
      default -> throw new IllegalArgumentException("Unknown user type: " + userType);
    };
  }
}
