package com.GestionAsisDocente.controller;

import com.GestionAsisDocente.dto.PublicacionesRequest;
import com.GestionAsisDocente.entity.Publicaciones;
import com.GestionAsisDocente.service.PublicacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicacionesController {

    @Autowired
    private PublicacionesService publicacionesService;

    @GetMapping("/get-all-publicaciones")
    public ResponseEntity<List<Publicaciones>> getAllPublicaciones() {
        return ResponseEntity.ok(publicacionesService.getAllPublicaciones());
    }

    @PostMapping("/create-publicacion")
    public ResponseEntity<Publicaciones> createPublicacion(@RequestBody PublicacionesRequest request) {
        return ResponseEntity.ok(publicacionesService.createPublicacion(request));
    }

    @PutMapping("/update-publicacion/{id}")
    public ResponseEntity<Publicaciones> updatePublicacion(@PathVariable Integer id,
                                                           @RequestBody PublicacionesRequest request) {
        return ResponseEntity.ok(publicacionesService.updatePublicacion(id, request));
    }

    @DeleteMapping("/delete-publicacion/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable Integer id) {
        publicacionesService.deletePublicacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-publicacion/{id}")
    public ResponseEntity<Publicaciones> getPublicacionById(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionesService.getPublicacionById(id));
    }

    @GetMapping("/get-publicaciones-ocultas")
    public ResponseEntity<List<Publicaciones>> getPublicacionesOcultas() {
        return ResponseEntity.ok(publicacionesService.getPublicacionesOcultas());
    }

    @GetMapping("/get-publicaciones-usuario/{userId}")
    public ResponseEntity<List<Publicaciones>> getPublicacionesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(publicacionesService.getPublicacionesByUser(userId));
    }
    @GetMapping("/reportes-busqueda-mascotas")
    public ResponseEntity<List<Publicaciones>> getReportesBusquedaMascotas() {
        return ResponseEntity.ok(publicacionesService.getReportesBusquedaMascotas());
    }
}
