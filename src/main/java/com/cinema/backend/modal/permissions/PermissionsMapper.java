package com.cinema.backend.modal.permissions;

import org.springframework.stereotype.Service;

import com.cinema.backend.modal.permissions.dto.PermissionRequest;
import com.cinema.backend.modal.permissions.dto.PermissionResponse;
import com.cinema.backend.modal.permissions.entity.Permissions;

@Service
public class PermissionsMapper {
  public Permissions toPermissions(PermissionRequest permissionsRequest) {
    return  Permissions.builder()
      .name(permissionsRequest.name())
      .apiPath(permissionsRequest.apiPath())
      .method(permissionsRequest.method())
      .module(permissionsRequest.module())
      .build();
  } 

  public PermissionResponse toPermissionResponse(Permissions permissions) {
    return PermissionResponse.builder()
      .id(permissions.getId())
      .name(permissions.getName())
      .apiPath(permissions.getApiPath())
      .method(permissions.getMethod())
      .module(permissions.getModule())
      .createdDate(permissions.getCreatedDate())
      .build();
  }
}
