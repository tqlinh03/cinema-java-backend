package com.cinema.backend.modal.permissions;

import org.springframework.web.bind.annotation.RestController;

import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.permissions.dto.PermissionRequest;
import com.cinema.backend.modal.permissions.dto.PermissionResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Tag(name = "Permissions")
public class PermissionsController {
  
  private final PermissionService permissionService;

  @PostMapping
  public ResponseEntity<String> createPermission(
    @RequestBody @Valid PermissionRequest permissionRequest
  ) {
    return ResponseEntity.ok(permissionService.create(permissionRequest));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> update(
    @PathVariable Integer id,
    @RequestBody @Valid PermissionRequest permissionRequest
  ) {
    return ResponseEntity.ok(permissionService.update(id, permissionRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    permissionService.delete(id);
    return ResponseEntity.ok("Xóa thành công permission có id = " + id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Integer> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(permissionService.findById(id));
  }

  @GetMapping
  public ResponseEntity<PageResponse<PermissionResponse>> getPermission(
    @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size
  ) {
    return ResponseEntity.ok(permissionService.findAll(page, size));
  }
}
