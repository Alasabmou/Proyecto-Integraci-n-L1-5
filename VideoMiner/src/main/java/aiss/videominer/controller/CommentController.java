package aiss.videominer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.VideoRepository;

@RestController
@RequestMapping("/api/videominer/comments")
@Tag(name = "Comment", description = "Operaciones relacionadas con los comentarios")
public class CommentController {
    
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    VideoRepository videoRepository;


    @Operation(summary = "Obtener todos los comentarios", description = "Devuelve una lista de todos los comentarios")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)))
    public List<Comment> findAll() {
        List<Comment> _comments = commentRepository.findAll();
        return _comments;
    }




    @Operation(summary = "Obtener un comentario por ID", description = "Devuelve el comentario asociado al ID dado")
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentario encontrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    public Comment findOne(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> _comments = commentRepository.findById(id);
        if (!_comments.isPresent()) {
            throw new CommentNotFoundException();
        }
        return _comments.get();
    }



    
    @Operation(summary = "Eliminar un comentario por ID", description = "Elimina el comentario asociado al ID dado")
    @ApiResponses(
            @ApiResponse(responseCode = "204", description = "Comentario eliminado",
                    content = @Content(schema = @Schema(implementation = Comment.class))))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable String id) {
        if(commentRepository.existsById(id)) commentRepository.deleteById(id);
    }

    
}
