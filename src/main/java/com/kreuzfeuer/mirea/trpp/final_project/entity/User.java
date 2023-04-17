package com.kreuzfeuer.mirea.trpp.final_project.entity;


import com.kreuzfeuer.mirea.trpp.final_project.entity.enums.Role;
import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 16, message = "Between 3 and 25 symbols")
    private String userName;

    @Column(unique = true)
    @Size(min=3,max = 16, message = "Between 3 and 37 symbols")
    private String login;

    @Column(name = "password",length = 1000)
    @Size(min = 2, max = 36, message = "Between 2 and 36 symbols")
    private String password;

    @Size(min = 4)
    private String email;

    @Column(name = "active")
    private boolean isActive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles  = new HashSet<>();

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
        fetch = FetchType.LAZY)
    private List<Book> books;

    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
