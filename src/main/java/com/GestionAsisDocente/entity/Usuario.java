package com.GestionAsisDocente.entity;

import com.GestionAsisDocente.repository.UsuarioRepo;
import com.GestionAsisDocente.service.UsuarioDetailsService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name="Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<RolUser> rolUsers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("=== DEBUG: getAuthorities() called for user: " + this.email + " ===");

        if (this.id == null) {
            System.out.println("User ID is null");
            return List.of();
        }

        try {
            UsuarioRepo repo = StaticRepositoryInjector.getUsuarioRepo();
            if (repo != null) {
                List<String> roleNames = repo.findRoleNamesByUserId(this.id);
                System.out.println("Found roles manually: " + roleNames);

                return roleNames.stream()
                        .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                        .collect(Collectors.toList());
            } else {
                System.out.println("Repository is null");
                return List.of();
            }
        } catch (Exception e) {
            System.out.println("Error loading roles: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private Set<DocenteMaterias> docenteMaterias;


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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public List<String> getRoleNames() {
        if (rolUsers == null) return List.of();
        return rolUsers.stream()
                .map(ru -> ru.getRol().getNombre()) // Cambié getRol_id() por getRol()
                .toList();
    }


}