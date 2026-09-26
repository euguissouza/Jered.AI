package com.api.jered.ai.api.Service;

import com.api.jered.ai.api.Tool.Prompt;
import dev.langchain4j.service.SystemMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProcessadorRequest {

    private AiEngine engine;

    public ProcessadorRequest(AiEngine engine) {
        this.engine = engine;
    }

    public ResponseEntity<Byte[]> DownloadFile(MultipartFile file) throws  Exception{
        try{
            Byte[] arquivos = engine.uploadArquivo(file);
            String nomeOriginal = file.getOriginalFilename();
            String nomeDownload = "Resumo" + nomeOriginal;
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeDownload + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(arquivos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
