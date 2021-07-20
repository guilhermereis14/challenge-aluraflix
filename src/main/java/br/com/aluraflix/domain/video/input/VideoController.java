package br.com.aluraflix.domain.video.input;

import br.com.aluraflix.domain.video.output.VideoEntity;
import br.com.aluraflix.domain.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        VideoEntity video = videoMapper.mapToVideoEntity(videoRequest);
        video = videoService.add(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoMapper.mapToVideoResponse(video));
    }
}
