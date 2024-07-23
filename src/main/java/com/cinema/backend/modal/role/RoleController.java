package com.cinema.backend.modal.role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.role.dto.RoleRequest;
import com.cinema.backend.modal.role.dto.RoleResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@Tag(name = "Role")
public class RoleController {

  public final RoleService roleService;

  @PostMapping
  public ResponseEntity<String> createRole(
    @RequestBody @Valid RoleRequest roleRequest
  ) {
    return ResponseEntity.ok(roleService.createRole(roleRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleResponse> findById(
    @PathVariable Integer id
  ) {
    return ResponseEntity.ok(roleService.findById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> updateRole(
    @PathVariable Integer id,
    @RequestBody @Valid RoleRequest roleRequest
  ) {
    return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteRole(
    @PathVariable Integer id
  ) {
    roleService.deleteRole(id);
    return ResponseEntity.accepted().body("Xóa thành công Role ID = " + id);
  }
  
  @GetMapping()
  public ResponseEntity<PageResponse<RoleResponse>> findAll(
    @RequestParam(name = "page", defaultValue ="0", required = false) Integer page,
    @RequestParam(name = "size", defaultValue ="10", required = false) Integer size
    ) {
      return ResponseEntity.ok(roleService.findAll(page, size));
  }
  
}
