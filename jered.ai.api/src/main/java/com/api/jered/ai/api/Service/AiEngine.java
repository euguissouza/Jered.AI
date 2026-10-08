package com.api.jered.ai.api.Service;



import com.google.genai.Client;

import com.google.genai.gaos.models.interactions.*;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.genai.types.Content;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class AiEngine {

    Client client = new Client();
    String model = "gemini-3.8-flash";

    private ProcessadorRequest request;

    public AiEngine(@Lazy ProcessadorRequest request, Client client) {
        this.request = request;
        this.client = client;
    }

    public byte[] processaDados(MultipartFile file) throws IOException {

            Part filePart = Part.fromBytes(file.getBytes(), file.getContentType());
            Part textPart = Part.fromText("Resuma para mim esse arquivo");

            Content content = Content.builder().parts(List.of(filePart, textPart)).build();

            GenerateContentResponse response = client.models.generateContent(
                    model,
                    content,
                    null
                    );

            String responseTxt = response.text();

        return responseTxt.getBytes(StandardCharsets.UTF_8);
    }


    public ResponseEntity<byte[]> geraDownload(@Param("file")  MultipartFile file) throws IOException {
        byte[] geraDoc = processaDados(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "imagem_gerada.png");
        return ResponseEntity.ok().headers(headers).body(geraDoc);
    }

}
