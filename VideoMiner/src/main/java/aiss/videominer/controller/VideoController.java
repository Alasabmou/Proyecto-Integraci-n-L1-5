package aiss.videominer.controller;


import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videominer/videos")
@Tag(name = "Videos", description = "Operaciones relacionadas con los vídeos")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    
    @Operation(
            summary = "Obtener todos los vídeos",
            description = "Devuelve una lista con todos los vídeos almacenados en el sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de vídeos recuperada", 
                    content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Video.class)), mediaType = "application/json") })
    })
    @GetMapping
    public List<Video> findAll() {
        List<Video> _videos = videoRepository.findAll();
        return _videos;
    }




    @Operation(
            summary = "Obtener un vídeo por ID",
            description = "Devuelve los detalles de un vídeo específico dado su identificador único"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vídeo encontrado", 
                    content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Vídeo no encontrado", 
                    content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public Video findOne(@Parameter(description = "id of video to be searched") @PathVariable String id) throws VideoNotFoundException {
        Optional<Video> _video = videoRepository.findById(id);
        
        if (!_video.isPresent()) {
            throw new VideoNotFoundException();
        }
        return _video.get();
    }




    @Operation(summary = "Listar comentarios de un video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentarios obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Video no encontrado")
    })
    @GetMapping("/{id}/comments")
    public List<Comment> findComments(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> _video = videoRepository.findById(id);

        if (!_video.isPresent()) {
            throw new VideoNotFoundException();
        }

        return _video.get().getComments();
    }


    
    
    @Operation(summary = "Listar captions de un video")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Captions obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "Video no encontrado")
    })
    @GetMapping("/{id}/captions")
    public List<Caption> findCaptions(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> _video = videoRepository.findById(id);

        if (!_video.isPresent()) {
            throw new VideoNotFoundException();
        }

        return _video.get().getCaptions();
    }
}
