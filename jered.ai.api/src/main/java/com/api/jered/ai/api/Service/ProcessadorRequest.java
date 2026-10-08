package com.api.jered.ai.api.Service;



import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.DocumentContent;
import com.google.genai.gaos.models.interactions.DocumentContentMimeType;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProcessadorRequest {

    private AiEngine engine;

    public ProcessadorRequest(AiEngine engine) {
        this.engine = engine;
    }


    //Aqui vai gerar um encoder para mandar para engine, a ideia é que converta o Arquivo em Bytes para
    //ser interpretado pela IA.
    public Content uploadFile (MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return DocumentContent.builder().data(base64)
                .mimeType(DocumentContentMimeType.APPLICATION_PDF).build();


    }

}
