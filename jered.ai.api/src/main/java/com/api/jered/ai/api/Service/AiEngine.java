package com.api.jered.ai.api.Service;



import com.api.jered.ai.api.ArquivosJered.Arquivos;
import com.google.genai.Client;

import com.google.genai.gaos.models.interactions.*;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import com.google.genai.types.Content;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class AiEngine {

    Client client = new Client();
    String model = "gemini-3.8-flash";



    public byte[] processaDados(MultipartFile arquivos) throws IOException {
            Part toIA = Part.fromBytes(arquivos.getBytes(), arquivos.getContentType());
            ClassPathResource markFile = new ClassPathResource("/Tool/prompt.md");
            String dataMark = StreamUtils.copyToString(markFile.getInputStream(), StandardCharsets.UTF_8);
            Part prompt = Part.fromText(dataMark);

            Content content = Content.builder().parts(List.of(toIA, prompt)).build();

            GenerateContentResponse response = client.models.generateContent(
                    model,
                    content,
                    null
                    );

            String responseTxt = response.text();

        return responseTxt.getBytes(StandardCharsets.UTF_8);
    }

}
