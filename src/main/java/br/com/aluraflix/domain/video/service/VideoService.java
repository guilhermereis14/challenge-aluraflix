package br.com.aluraflix.domain.video.service;

import br.com.aluraflix.domain.video.output.VideoDAO;
import br.com.aluraflix.domain.video.output.VideoEntity;
import br.com.aluraflix.infraestructure.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VideoService {

    @Autowired
    private VideoDAO videoDAO;

    public VideoEntity add(VideoEntity video) {
        log.info("C=VideoService, M=add, Video={}", video);
        video.initialize();
        return videoDAO.save(video);
    }

    public VideoEntity update(String id, VideoEntity video) {
        log.info("C=VideoService, M=update, id={}, Video={}", id, video);
        VideoEntity videoToUpdate = getById(id);
        BeanUtils.copyProperties(video, videoToUpdate, "id", "status");
        return videoDAO.save(videoToUpdate);
    }

    public void markVideoToHidden(String id) {
        log.info("C=VideoService, M=markVideoToHidden, id={}", id);
        VideoEntity video = getById(id);
        video.markToHidden();
        videoDAO.save(video);
    }

    public List<VideoEntity> fetchAll() {
        return (List<VideoEntity>) videoDAO.findAll();
    }

    public VideoEntity getById(String id) {
        return videoDAO.findById(id)
                .orElseThrow(() -> new ServiceException("Video não encontrado com o id informado"));
    }
}
