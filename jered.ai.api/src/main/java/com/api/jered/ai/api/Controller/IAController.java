package com.api.jered.ai.api.Controller;


import com.api.jered.ai.api.Service.AiEngine;
import com.api.jered.ai.api.Service.ProcessadorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.io.IOException;

@Controller
@RequestMapping("/api/jered")
@CrossOrigin ("*")
public class IAController {


    private ProcessadorRequest request;
    private AiEngine engine;

    public IAController(ProcessadorRequest request, AiEngine engine) {
        this.request = request;
        this.engine = engine;
    }


    @PostMapping("/post")
    public String upload(@RequestParam("file")MultipartFile file)throws IOException{
        request.uploadFile(file);

        return "redirect:/api/jered/download";
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> get(@RequestParam("file")MultipartFile file) throws IOException {
        return engine.geraDownload(file);
    }



}
