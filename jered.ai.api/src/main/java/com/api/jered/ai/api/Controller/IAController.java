package com.api.jered.ai.api.Controller;



import com.api.jered.ai.api.ArquivosJered.Arquivos;
import com.api.jered.ai.api.Service.ProcessadorRequest;
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


    private ProcessadorRequest request;

    public IAController(ProcessadorRequest request) {
        this.request = request;
    }


    @PostMapping("/post")
    public ResponseEntity<Void> upload(@RequestParam("file") Arquivos arquivos) throws IOException {
        request.uploadFile(arquivos);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create("/api/jered/download"))
                .build();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(MultipartFile file) throws IOException {
        return request.geraDownload(file); // usa o resultado já gerado
    }



}
