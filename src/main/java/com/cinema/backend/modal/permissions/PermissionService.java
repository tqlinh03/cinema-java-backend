package com.cinema.backend.modal.permissions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.permissions.dto.PermissionRequest;
import com.cinema.backend.modal.permissions.dto.PermissionResponse;
import com.cinema.backend.modal.permissions.entity.Permissions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {
  private final PermissionsRepository permissionRepository;
  private final PermissionsMapper permissionsMapper;

  public String create(PermissionRequest permissionRequest) {
    Permissions permission = permissionsMapper.toPermissions(permissionRequest);
    return permissionRepository.save(permission).getName();
  }

  public String update(Integer id, PermissionRequest permissionRequest) {
    var permission = permissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("không tìm thất permission với id: " + id));

    permission.setName(permissionRequest.name());
    permission.setApiPath(permissionRequest.apiPath());
    permission.setMethod(permissionRequest.method());
    permission.setModule(permissionRequest.module());

    return permissionRepository.save(permission).getName();
  }

  public void delete(Integer id) {
    var permission = permissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("không tìm thất permission với id: " + id));
    permissionRepository.delete(permission);
  }

  public Integer findById(Integer id) {
    var permission = permissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("không tìm thất permission với id: " + id));
    return permissionRepository.save(permission).getId();
  }

  public PageResponse<PermissionResponse> findAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Permissions> permissions = permissionRepository.findAllPermissions(pageable);
    List<PermissionResponse> permissionsResponse = permissions.stream()
        .map(permissionsMapper::toPermissionResponse)
        .toList();
    Meta meta =  Meta.builder()
        .number(permissions.getNumber())
        .size(permissions.getSize())
        .totalElements(permissions.getTotalElements())
        .totalPages(permissions.getTotalPages())
        .last(permissions.isLast()) 
        .first(permissions.isFirst())
        .build();
    return new PageResponse<>(
        permissionsResponse, 
        meta
        );
  }
}
