package com.GestionAsisDocente.controller;

import com.GestionAsisDocente.entity.RolUser;
import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.RolUserRepository;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class DebugController {

    @Autowired
    private UsuarioRepo ourUsersRepository;

    @Autowired
    private RolUserRepository rolUserRepository;

    @GetMapping("/full-check/{email}")
    public ResponseEntity<?> fullCheck(@PathVariable String email) {
        Map<String, Object> result = new HashMap<>();

        // 1. Verificar usuario
        Optional<Usuario> userOpt = ourUsersRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            result.put("userFound", true);
            result.put("userId", user.getId());
            result.put("userEmail", user.getEmail());

            // 2. Verificar rolUsers en el usuario
            result.put("rolUsersSize", user.getRolUsers() != null ? user.getRolUsers().size() : 0);

            // 3. Buscar directamente en rol_user
            List<RolUser> directRoles = rolUserRepository.findAll().stream()
                    .filter(ru -> ru.getUsuario() != null && ru.getUsuario().getId().equals(user.getId()))
                    .collect(Collectors.toList());

            result.put("directRolesFound", directRoles.size());

            List<String> roleNames = directRoles.stream()
                    .map(ru -> ru.getRol() != null ? ru.getRol().getNombre() : "null")
                    .collect(Collectors.toList());
            result.put("roleNames", roleNames);

            // 4. Probar getAuthorities
            try {
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                result.put("authoritiesSize", authorities.size());
                result.put("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
            } catch (Exception e) {
                result.put("authoritiesError", e.getMessage());
            }

        } else {
            result.put("userFound", false);
        }

        return ResponseEntity.ok(result);
    }
}