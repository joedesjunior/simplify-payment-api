package com.picpay.simplify_payment_api.controller;

import com.picpay.simplify_payment_api.dto.request.UserDtoRequest;
import com.picpay.simplify_payment_api.dto.response.UserDtoResponse;
import com.picpay.simplify_payment_api.exception.UserAlreadyExistsException;
import com.picpay.simplify_payment_api.service.UserRegistrationFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserRegistrationFactory registrationFactory;

  public UserController(UserRegistrationFactory registrationFactory) {
    this.registrationFactory = registrationFactory;
  }

  @Operation(
      method = "POST",
      description = "This endpoint allows the registration of a new user.",
      responses = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "409", description = "User already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PostMapping("/register")
  public ResponseEntity<UserDtoResponse> register(
      @Valid @RequestBody UserDtoRequest userDtoRequest) {

    try {
      logger.info("Received request to register user: {}", userDtoRequest.getEmail());
      var strategy = registrationFactory.getStrategy(userDtoRequest.getUserType());

      var user = strategy.register(userDtoRequest);
      logger.info("User registered successfully with ID: {}", user.getId());
      return ResponseEntity.created(URI.create("/api/users/register" + user.getId())).body(user);
    } catch (UserAlreadyExistsException e) {
      logger.warn("User registration failed: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
    }
  }
}
