package com.gouasmia.dev.socialnetbooks.user;

import com.gouasmia.dev.socialnetbooks.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(EntityListeners.class)
public class User implements UserDetails, Principal {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String firstname;
    private String lastName;
    private String password;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private boolean accountLocked;
    private boolean enabled;
    @CreatedDate()
    @Column(nullable = false, updatable = false)
    private LocalDate createDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public String getName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
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
        return lastName + " " + firstname;
    }

}
