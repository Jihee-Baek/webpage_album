package com.prj.sbforsvc.Photo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

@Controller
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/photo")
    public ResponseEntity<?> createPhoto(@Validated @RequestParam("files") List<MultipartFile> files) throws Exception {

        photoService.addPhoto(Photo.builder().build(), files);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/photo")
    public String getPhoto(@RequestParam Long idx) {

        Photo photo = photoService.findPhoto(idx).orElseThrow(RuntimeException::new);
        String imgPath = photo.getStored_file_path();
        return "<img src=/" + imgPath + ">";
    }

}
