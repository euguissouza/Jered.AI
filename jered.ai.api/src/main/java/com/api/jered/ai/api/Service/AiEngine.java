package com.api.jered.ai.api.Service;



import com.google.genai.Client;

import com.google.genai.gaos.models.interactions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


@Service
public class AiEngine {

    Client client = new Client();

    private ProcessadorRequest request;

    public AiEngine(@Lazy ProcessadorRequest request, Client client) {
        this.request = request;
        this.client = client;
    }

    public byte[] processaDados(MultipartFile file) throws IOException {
        Content docContent = request.uploadFile(file);

        Content textContent = TextContent.builder()
                .text("Resuma para mim esse arquivo e me devolva como .pdf")
                .build();

        CreateModelInteraction llmInteraciton = CreateModelInteraction.builder()
                .model(Model.of("gemini-3.8-flash"))
                .input(InteractionsInput.ofContent(List.of(docContent, textContent)))
                .build();

        return new byte[0];
    }


    public ResponseEntity<byte[]> geraDownload(@Param("file")  MultipartFile file) throws IOException {
        byte[] geraDoc = processaDados(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "imagem_gerada.png");
        return ResponseEntity.ok().headers(headers).body(geraDoc);
    }

}
