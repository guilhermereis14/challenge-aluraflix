package br.com.aluraflix.domain.video.input;

import br.com.aluraflix.domain.video.output.VideoEntity;
import br.com.aluraflix.domain.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/api/videos")
@RestController
@Api(value = "videos", description = "Web-service de Vídeos")
public class VideoController {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoService videoService;

    @PostMapping
    @ApiOperation(value = "Cadastra um novo vídeo")
    public ResponseEntity<VideoResponse> add(@RequestBody @Valid VideoRequest videoRequest) {
        log.info("C=VideoController, M=add, VideoRequest={}", videoRequest);
        VideoEntity video = videoMapper.mapToVideoEntity(videoRequest);
        video = videoService.add(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoMapper.mapToVideoResponse(video));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca vídeo pelo Id")
    public ResponseEntity<VideoResponse> getById(@PathVariable String id) {
        VideoEntity video = videoService.getById(id);
        return ResponseEntity.ok(videoMapper.mapToVideoResponse(video));
    }

    @GetMapping
    @ApiOperation(value = "Busca todos os vídeos")
    public ResponseEntity<List<VideoResponse>> fetchAll() {
        List<VideoEntity> videos = videoService.fetchAll();
        return ResponseEntity.ok(videos.stream().map(v -> videoMapper.mapToVideoResponse(v)).collect(Collectors.toList()));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualiza um video a partir do Id")
    public ResponseEntity<VideoResponse> update(@PathVariable String id,
                                                @RequestBody VideoRequest videoRequest) {
        log.info("C=VideoController, M=update, id={}, VideoRequest={}", id, videoRequest);
        VideoEntity videoToUpdate = videoMapper.mapToVideoEntity(videoRequest);
        VideoEntity videoUpdated = videoService.update(id, videoToUpdate);
        return ResponseEntity.ok(videoMapper.mapToVideoResponse(videoUpdated));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Marcar vídeo como oculto")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("C=VideoController, M=delete, id={}", id);
        videoService.markVideoToHidden(id);
        return ResponseEntity.noContent().build();
    }
}
