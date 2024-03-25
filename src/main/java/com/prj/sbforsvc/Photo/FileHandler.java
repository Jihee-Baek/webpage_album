package com.prj.sbforsvc.Photo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandler {

    public List<Photo> parserFileInfo(Long photoIdx, List<MultipartFile> multipartFiles) throws Exception {

        List<Photo> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "\\";

        String path = "images/" + current_date;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalFileExtension = ".gif";
                } else {
                    break;
                }

                String new_file_name = System.nanoTime() + originalFileExtension;

                Photo photo = Photo.builder()
                        .origin_file_name(multipartFile.getOriginalFilename())
                        .stored_file_path(path + "/" + new_file_name)
                        .file_size(multipartFile.getSize())
                        .create_at(LocalDateTime.now())
                        .build();

                fileList.add(photo);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
    }
}
