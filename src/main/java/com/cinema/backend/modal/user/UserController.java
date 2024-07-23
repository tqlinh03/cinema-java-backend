package com.cinema.backend.modal.user;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.user.dto.ChangePasswordRequest;
import com.cinema.backend.modal.user.dto.UserRequest;
import com.cinema.backend.modal.user.dto.UserResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

  private final UserService userService;
  
  // @PostMapping
  // public ResponseEntity<Integer> create(
  //   @RequestBody  @Valid UserRequest userRequest
  // ) {
  //   return ResponseEntity.ok(userService.create(userRequest));
  // }

  @PatchMapping("/change-password")
  public ResponseEntity<?> changePassword(
    @RequestBody @Valid ChangePasswordRequest changePasswordRequest,
    Principal connectedUser
  ) {
    
    return ResponseEntity.ok(userService.changePassword(changePasswordRequest, connectedUser));
  }

  @GetMapping
  public ResponseEntity<PageResponse<UserResponse>> getAll(
    @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size
  ) {
    return ResponseEntity.ok(userService.getAll(page, size));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Integer> updateUserToken(
    @PathVariable Integer id,
    @RequestBody @Valid UserRequest userRequest
  ) {
    return ResponseEntity.ok(userService.update(id, userRequest));
  }
  
}
