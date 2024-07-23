package com.cinema.backend.modal.role;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.backend.modal.permissions.PermissionsRepository;
import com.cinema.backend.modal.role.dto.RoleRequest;
import com.cinema.backend.modal.role.dto.RoleResponse;
import com.cinema.backend.modal.role.entities.Role;
import com.cinema.backend.modal.permissions.entity.Permissions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleMapper {
  private final PermissionsRepository permissionRepository;

  public Role toRole(RoleRequest roleRequest) {
    List<Permissions> permission = permissionRepository.findAllById(roleRequest.permissions()); 
    return Role.builder()
      .name(roleRequest.name()) 
      .description(roleRequest.description())
      .isActive(roleRequest.isActive())
      .permissions(permission)
      .build();
  }

  public RoleResponse toRoleRespone(Role role) {
    return RoleResponse.builder()
      .id(role.getId())
      .name(role.getName())
      .description(role.getDescription())
      .isActive(role.isActive())
      .createdDate(role.getCreatedDate())
      .lastModifiedDate(role.getLastModifiedDate())
      .permissions(role.getPermissions())
      .build();
  }
}
