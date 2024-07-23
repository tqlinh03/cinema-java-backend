package com.cinema.backend.modal.role;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.permissions.PermissionsRepository;
import com.cinema.backend.modal.permissions.entity.Permissions;
import com.cinema.backend.modal.role.dto.RoleRequest;
import com.cinema.backend.modal.role.dto.RoleResponse;
import com.cinema.backend.modal.role.entities.Role;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;
  private final PermissionsRepository permissionRepository;

  public String createRole(RoleRequest roleRequest) {
    Role role = roleMapper.toRole(roleRequest);
    return roleRepository.save(role).getName();
  }

  public RoleResponse findById(Integer id) {
    return roleRepository.findById(id)
      .map(roleMapper::toRoleRespone)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Role id =" + id));
  }

  public String updateRole(Integer id, RoleRequest roleRequest) {
    List<Permissions> permission = permissionRepository.findAllById(roleRequest.permissions());
    Role role = roleRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Role có id =" + id));
    role.setName(roleRequest.name());
    role.setDescription(roleRequest.description());
    role.setActive(roleRequest.isActive());
    role.setPermissions(permission);
    return roleRepository.save(role).getName();
  }

  public void deleteRole(Integer id) {
    Role role = roleRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Role có id =" + id));
      if ("ADMIN".equals(role.getName()) || "USER".equals(role.getName()) || "SUPER_ADMIN".equals(role.getName())) {
        throw new EntityNotFoundException("Không thể xóa vai trò có tên: " + role.getName());
    }
    roleRepository.delete(role);
  }

  public PageResponse<RoleResponse> findAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Role> roles = roleRepository.findAllRoles(pageable);
    List<RoleResponse> roleResponse = roles.stream()
      .map(roleMapper::toRoleRespone)
      .toList();
    Meta meta =  Meta.builder()
    .number(roles.getNumber())
    .size(roles.getSize())
    .totalElements(roles.getTotalElements())
    .totalPages(roles.getTotalPages())
    .last(roles.isLast()) 
    .first(roles.isFirst())
    .build();
    return new PageResponse<>( 
      roleResponse, 
      meta
    );
  }


}
