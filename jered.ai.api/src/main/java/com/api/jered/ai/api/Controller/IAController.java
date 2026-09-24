package com.api.jered.ai.api.Controller;


import com.api.jered.ai.api.Service.ProcessadorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jered")
public class IAController {

    private ProcessadorRequest request;

    public IAController(ProcessadorRequest request) {
        this.request = request;
    }


    @PostMapping("/post")
    public ResponseEntity<Byte[]> enviaDownload(@RequestParam("file")MultipartFile file)throws Exception{
        return request.DownloadFile(file);
    }



}
