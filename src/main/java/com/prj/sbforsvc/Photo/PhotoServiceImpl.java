package com.prj.sbforsvc.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        this.fileHandler = new FileHandler();
    }

    public Photo addPhoto(Photo photo, List<MultipartFile> files) throws Exception {

        List<Photo> list = fileHandler.parserFileInfo(photo.getIdx(), files);

        if (list.isEmpty()) {

        } else {
            List<Photo> pictureBeans = new ArrayList<>();
            for (Photo photos : list) {
                pictureBeans.add(photoRepository.save(photos));
            }
        }

        return photoRepository.save(photo);
    }

    public List<Photo> findPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> findPhoto(Long idx) {
        return photoRepository.findById(idx);
    }
}
