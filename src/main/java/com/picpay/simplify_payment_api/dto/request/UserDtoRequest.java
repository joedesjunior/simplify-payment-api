package com.picpay.simplify_payment_api.dto.request;

import com.picpay.simplify_payment_api.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDtoRequest {
  @NotBlank private String fullName;

  @NotBlank private String documentNumber;

  @NotBlank @Email private String email;

  @NotBlank private String password;

  @NotNull private UserType userType;
}
