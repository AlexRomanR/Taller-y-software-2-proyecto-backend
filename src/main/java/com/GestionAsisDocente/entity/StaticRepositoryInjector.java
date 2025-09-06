package com.GestionAsisDocente.entity;

import com.GestionAsisDocente.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticRepositoryInjector {

    private static UsuarioRepo usuarioRepo;

    @Autowired
    public StaticRepositoryInjector(UsuarioRepo usuarioRepo) {
        StaticRepositoryInjector.usuarioRepo = usuarioRepo;
    }

    public static UsuarioRepo getUsuarioRepo() {
        return usuarioRepo;
    }
}