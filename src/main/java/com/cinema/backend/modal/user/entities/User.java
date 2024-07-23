package com.cinema.backend.modal.user.entities;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.cinema.backend.modal.order.Order;
import com.cinema.backend.modal.store.Store;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cinema.backend.modal.role.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

  @Id
  @GeneratedValue
  private Integer id;
  private String firstName; 
  private String lastName;
  private LocalDate dateOfBirth;
  @Column(unique = true)
  private String email; 
  @JsonIgnore
  private String password;
  private Boolean accountLocked;
  private Boolean enabled;
  // private String refreshToken;
  // @ManyToMany(fetch = FetchType.EAGER)
  // @ManyToMany(fetch = FetchType.EAGER, mappedBy = "user")
  @ManyToOne(fetch = FetchType.EAGER)
  private Role role;

  @ManyToOne
  private Store store;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;



  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;
 
  @Override
  public String getName() {
    return lastName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return  Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !accountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public String getFullName() {
    return firstName + " " + lastName;
}

}
