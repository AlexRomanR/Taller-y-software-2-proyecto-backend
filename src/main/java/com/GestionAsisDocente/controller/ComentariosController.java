package com.GestionAsisDocente.controller;

import com.GestionAsisDocente.dto.ComentariosRequest;
import com.GestionAsisDocente.entity.Comentarios;
import com.GestionAsisDocente.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminuser")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @GetMapping("/get-all-comentarios")
    public ResponseEntity<List<Comentarios>> getAllComentarios() {
        return ResponseEntity.ok(comentariosService.getAllComentarios());
    }

    @PostMapping("/create-comentario")
    public ResponseEntity<Comentarios> createComentario(@RequestBody ComentariosRequest request) {
        return ResponseEntity.ok(comentariosService.createComentario(request));
    }

    @PutMapping("/update-comentario/{id}")
    public ResponseEntity<Comentarios> updateComentario(@PathVariable Integer id, @RequestBody ComentariosRequest request) {
        return ResponseEntity.ok(comentariosService.updateComentario(id, request));
    }

    @DeleteMapping("/delete-comentario/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        comentariosService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-comentario/{id}")
    public ResponseEntity<Comentarios> getComentarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(comentariosService.getComentarioById(id));
    }
}
