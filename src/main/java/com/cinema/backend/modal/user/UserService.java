package com.cinema.backend.modal.user;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.role.RoleRepository;
import com.cinema.backend.modal.user.dto.ChangePasswordRequest;
import com.cinema.backend.modal.user.dto.UserRequest;
import com.cinema.backend.modal.user.dto.UserResponse;
import com.cinema.backend.modal.user.entities.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;

  // public Integer create(UserRequest userRequest) {
  // User user = userMapper.toUser(userRequest);
  // System.out.printf("User: {}", user);

  // // return userRepository.save(userRequest.toEntity()).getId();
  // return 1;
  // }

  public void updateUserToken(String refreshToken, String email) {
    var user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalStateException("User not found"));
    // user.setRefreshToken(refreshToken);
    userRepository.save(user);
  }

  public String changePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser) {
    var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    // check if the current password is correct
    if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
      throw new IllegalStateException("Sai mật khẩu");
    }
    // check if the two new passwords are the same
    if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
      throw new IllegalStateException("Mật khẩu mới không khớp");
    }

    // update the password
    user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

    // save the new password
    repository.save(user);

    return "Đổi mật khẩu thành công";
  }

  public PageResponse<UserResponse> getAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());
    Page<User> users = userRepository.findAllUser(pageable);
    List<UserResponse> userResponses = users.stream()
        .map(userMapper::toUserResponse)
        .toList();
    Meta meta = Meta.builder()
        .number(users.getNumber())
        .size(users.getSize())
        .totalElements(users.getTotalElements())
        .totalPages(users.getTotalPages())
        .last(users.isLast())
        .first(users.isFirst())
        .build();
    return new PageResponse<>(
        userResponses,
        meta); 
  }

  public Integer update(Integer id, @Valid UserRequest userRequest) {
    var role = roleRepository.findById(userRequest.roleId())
        .orElseThrow(() -> new IllegalStateException("Không tìm thấy RoleId = " + userRequest.roleId()));
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Không tìm thấy UserId = " + id));  
    user.setFirstName(userRequest.firstName());
    user.setLastName(userRequest.lastName());
    user.setDateOfBirth(userRequest.dateOfBirth());
    user.setAccountLocked(userRequest.accountLocked());
    user.setRole(role);

   return userRepository.save(user).getId();
  }
}
