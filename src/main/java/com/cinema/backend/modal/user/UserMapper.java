package com.cinema.backend.modal.user;

import org.springframework.stereotype.Service;

import com.cinema.backend.modal.role.dto.RoleResponse;
import com.cinema.backend.modal.user.dto.UserResponse;
import com.cinema.backend.modal.user.entities.User;

@Service
public class UserMapper {
  // public User toUser(User user) {
  //   return User.builder()
  //       .id(user.id())
  //       .firstName(user.firstName())
  //       .lastName(user.lastName())
  //       .dateOfBirth(user.dateOfBirth())
  //       .email(user.email())
  //       // .password(user.password())
  //       .accountLocked(user.accountLocked())
  //       .enabled(user.enabled())
  //       .build();
  // }

  public UserResponse toUserResponse(User user) {
    RoleResponse role = RoleResponse.builder()
        .id(user.getRole().getId())
        .name(user.getRole().getName())
        .permissions(user.getRole().getPermissions())
        .build();
    return UserResponse.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .dateOfBirth(user.getDateOfBirth())
        .email(user.getEmail())
        .accountLocked(user.getAccountLocked())
        .enabled(user.getEnabled())
        .createDate(user.getCreateDate())
        .lastModifiedDate(user.getLastModifiedDate())
        .role(role)
        .build();
  }
}
