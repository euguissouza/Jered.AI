package com.api.jered.ai.api.Service;



import com.api.jered.ai.api.ArquivosJered.Arquivos;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DownloadService {

    private AiEngine engine;
    public DownloadService(AiEngine engine) {
        this.engine = engine;
    }

    //Função dedicada apenas para receber o arquivo preparado pela IA
    public ResponseEntity<byte[]> geraDownload(@Param("file") MultipartFile file) throws IOException {
        byte[] geraDoc = engine.processaDados(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Arquivo-resumido");
        return ResponseEntity.ok().headers(headers).body(geraDoc);
    }
}
