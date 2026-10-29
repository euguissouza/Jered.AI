package com.api.jered.ai.api.Service;

import com.api.jered.ai.api.ArquivosJered.Arquivos;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.DocumentContent;
import com.google.genai.gaos.models.interactions.DocumentContentMimeType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UploadService {
    //Aqui vai gerar um encoder para mandar para engine, a ideia é que converta o Arquivo em Bytes para
    //ser interpretado pela IA.
    public Content uploadFile (MultipartFile file) throws Exception {
        Arquivos arquivos = new Arquivos();
        String base64 = null;
        try {
            arquivos.setFile(file);
            byte[] bytes = arquivos.getFile().getBytes();
            base64 = Base64.getEncoder().encodeToString(bytes);

        }catch (Exception e){
            new RuntimeException(e);
        }
        return DocumentContent.builder().data(base64).mimeType(DocumentContentMimeType.APPLICATION_PDF).build();
    }
}
