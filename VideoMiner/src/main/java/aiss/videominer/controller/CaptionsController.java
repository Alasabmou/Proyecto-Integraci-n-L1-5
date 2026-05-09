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

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/videominer/captions")
public class CaptionsController {
     @Autowired
    CaptionRepository captionsRepository;

    @Autowired
    VideoRepository videoRepository;

    @Operation(summary = "Obtener todas las captions", description = "Devuelve una lista de todas las captions almacenadas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de captions obtenida correctamente",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Caption.class))))
    })
    @GetMapping
    public List<Caption> findAll() {
        List<Caption> _captions = captionsRepository.findAll();
        return _captions;
    }


    

    @Operation(summary = "Obtener una caption por ID", description = "Devuelve la caption asociada al ID dado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Caption encontrada",
                content = @Content(schema = @Schema(implementation = Caption.class))),
        @ApiResponse(responseCode = "404", description = "Caption no encontrada")
    })
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> _captions = captionsRepository.findById(id);
        if (!_captions.isPresent()) {
            throw new CaptionNotFoundException();
        }
        return _captions.get();
    }
   




    @Operation(summary = "Eliminar una caption por ID", description = "Elimina la caption asociada al ID dado")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Caption eliminada"),
        @ApiResponse(responseCode = "404", description = "Caption no encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable String id) throws CaptionNotFoundException {
        if(!captionsRepository.existsById(id)){
            throw new CaptionNotFoundException();
        }
        captionsRepository.deleteById(id);
    }
}
