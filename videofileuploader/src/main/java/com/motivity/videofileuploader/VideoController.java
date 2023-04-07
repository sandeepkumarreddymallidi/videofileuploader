package com.motivity.videofileuploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin("http://localhost:3000/")
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private FileRepository fileRepository;
    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxFileSize;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description) {
        try {
            Video video = new Video();
            video.setName(name);
            video.setDescription(description);
            video.setFileName(file.getOriginalFilename());
            video.setContentType(file.getContentType());
            video.setData(file.getBytes());
            if (file.getSize() > maxFileSize.toBytes()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("File size exceeds the limit of " + maxFileSize.toMegabytes() + "MB");
            }
            else {
                videoRepository.save(video);
                return ResponseEntity.status(HttpStatus.OK).body("Video uploaded successfully.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to upload video.");
        }
    }
    @GetMapping("/videos")
    public ResponseEntity<byte[]> getVideo() {
        Video video = videoRepository.findById(1l).orElse(null);
        if (video == null) {
            return ResponseEntity.notFound().build();
        }



        byte[] videoBytes = video.getData();

       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoBytes.length);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(video.getName()).build());*/
        return ResponseEntity.ok().body(videoBytes);
    }
    @PostMapping("/fileupload")
    public ResponseEntity<String> uploadImageFile(@RequestParam("file") MultipartFile file) throws IOException {
        if(file!=null){
            FileStorage fileStorage=new FileStorage();
            fileStorage.setFileName(file.getOriginalFilename());
            fileStorage.setData(file.getBytes());
            fileStorage.setContentType(file.getContentType());
            fileRepository.save(fileStorage);
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("please send the file");
        }
    }
    @GetMapping("/files")
    public ResponseEntity<byte[]> getImage() {
        FileStorage file = fileRepository.findById(1l).orElse(null);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] videoBytes = file.getData();
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoBytes.length);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(video.getName()).build());*/
        return ResponseEntity.ok().body(videoBytes);
    }
}