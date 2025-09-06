package com.GestionAsisDocente.service;

import com.GestionAsisDocente.entity.Usuario;
import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== USERDETAILSSERVICE: Loading user: " + username + " ===");

        Usuario usuario = usuarioRepo.findByEmailWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Loaded user ID: " + usuario.getId() + ", Email: " + usuario.getEmail());
        System.out.println("RolUsers after JOIN FETCH: " + (usuario.getRolUsers() != null ? usuario.getRolUsers().size() : "null"));

        // Forzar la inicialización de la colección
        if (usuario.getRolUsers() != null) {
            usuario.getRolUsers().forEach(ru -> {
                System.out.println("RolUser loaded: " + ru.getId() + " -> Rol: " + ru.getRol().getNombre());
            });
        }

        return usuario;
    }

}