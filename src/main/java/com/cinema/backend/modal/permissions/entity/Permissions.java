package com.cinema.backend.modal.permissions.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cinema.backend.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder 
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Permissions extends BaseEntity {
  @Column(unique = true)
  private String name;
  private String apiPath;
  private String method;
  private String module;
}
