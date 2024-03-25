package com.prj.sbforsvc.Photo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    public Photo addPhoto(Photo photo, List<MultipartFile> files) throws Exception;

    public List<Photo> findPhotos();

    public Optional<Photo> findPhoto(Long idx);
}
