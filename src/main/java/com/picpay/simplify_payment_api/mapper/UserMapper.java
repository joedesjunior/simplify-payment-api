package com.picpay.simplify_payment_api.mapper;

import com.picpay.simplify_payment_api.dto.request.UserDtoRequest;
import com.picpay.simplify_payment_api.dto.response.UserDtoResponse;
import com.picpay.simplify_payment_api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toEntity(UserDtoRequest userDtoRequest);

  UserDtoResponse toUserDtoResponse(User user);
}
