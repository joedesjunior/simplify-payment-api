package com.picpay.simplify_payment_api.service.strategy;

import com.picpay.simplify_payment_api.dto.request.UserDtoRequest;
import com.picpay.simplify_payment_api.dto.response.UserDtoResponse;

public interface UserRegistrationStrategy {
  UserDtoResponse register(UserDtoRequest userDtoRequest);
}
