package aiss.videominer.controller;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/videominer/channels")
@Tag(name = "Channel", description = "Operaciones relacionadas con canales")

public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;


    @Operation(summary = "Obtener todos los canales", description = "Devuelve una lista de todos los canales registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de canales devuelta con éxito",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Channel.class))))
    })
    @GetMapping
    public List<Channel> getAllChannels() {
        List<Channel> _channels = channelRepository.findAll();
        return _channels;
    }




    @Operation(summary = "Obtener un canal por ID", description = "Devuelve el canal asociado al ID dado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Canal encontrado",
                content = @Content(schema = @Schema(implementation = Channel.class))),
        @ApiResponse(responseCode = "404", description = "Canal no encontrado")
    })
    @GetMapping("/{id}")
    public Channel getCommentById(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> _channel = channelRepository.findById(id);
        if(!_channel.isPresent()){
            throw new ChannelNotFoundException();
        }
        return _channel.get();
    }




    @Operation(summary = "Crear un canal", description = "Crea un nuevo canal con sus vídeos, comentarios, captions y usuario asociado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Canal creado con éxito",
                content = @Content(schema = @Schema(implementation = Channel.class))),
        @ApiResponse(responseCode = "400", description = "Datos del canal incorrectos o mal formados")
    })
    @PostMapping
    public Channel createChannel(@RequestBody @Valid Channel channel) {
        return channelRepository.save(channel);
    } 




   @Operation(summary = "Crear un vídeo en un canal", description = "Añade un nuevo vídeo al canal especificado por su ID")
   @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Vídeo creado con éxito",
                content = @Content(schema = @Schema(implementation = Video.class))),
        @ApiResponse(responseCode = "400", description = "Datos del vídeo incorrectos o mal formados"),
        @ApiResponse(responseCode = "404", description = "Canal no encontrado")
    })
    @PostMapping("/{id}/videos")
    public Video createVideoChannel(@PathVariable String id, @RequestBody @Valid Video video) throws ChannelNotFoundException {
        Optional<Channel> _channel = channelRepository.findById(id);
        
        if(!_channel.isPresent()){
            throw new ChannelNotFoundException();
        }
        
        Channel channel = _channel.get();
        channel.getVideos().add(video);
        channelRepository.save(channel);

        return video;
    }



    
    @Operation(summary = "Eliminar un canal por ID", description = "Elimina el canal asociado al ID dado")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Canal eliminado"),
        @ApiResponse(responseCode = "404", description = "Canal no encontrado")
})
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable String id) throws ChannelNotFoundException {
        if(!channelRepository.existsById(id)){
            throw new ChannelNotFoundException();
        }
        channelRepository.deleteById(id);
    }
}