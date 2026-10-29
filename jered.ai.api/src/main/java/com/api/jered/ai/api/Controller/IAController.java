package com.api.jered.ai.api.Controller;



import com.api.jered.ai.api.ArquivosJered.Arquivos;
import com.api.jered.ai.api.Service.DownloadService;
import com.api.jered.ai.api.Service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Controller
@RequestMapping("/api/jered")
@CrossOrigin ("*")
public class IAController {


    private DownloadService downloadService;
    private UploadService uploadService;

    public IAController(DownloadService service,UploadService uploadService) {
        this.downloadService = service;
        this.uploadService = uploadService;
    }


    @PostMapping("/post")
    public ResponseEntity<Void> upload(@RequestParam("file")MultipartFile file) throws Exception {
        uploadService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create("/api/jered/download"))
                .build();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(MultipartFile file) throws IOException {
        return downloadService.geraDownload(file);
    }

}
