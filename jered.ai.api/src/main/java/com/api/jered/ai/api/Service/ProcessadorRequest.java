package com.api.jered.ai.api.Service;



import com.api.jered.ai.api.ArquivosJered.Arquivos;
import com.google.genai.gaos.models.interactions.Content;
import com.google.genai.gaos.models.interactions.DocumentContent;
import com.google.genai.gaos.models.interactions.DocumentContentMimeType;

import com.google.genai.types.Part;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProcessadorRequest {


    private AiEngine engine;

    public ProcessadorRequest(AiEngine engines) {
        this.engine = engine;
    }


    //Aqui vai gerar um encoder para mandar para engine, a ideia é que converta o Arquivo em Bytes para
    //ser interpretado pela IA.
    public Content uploadFile (Arquivos arquivos) throws IOException {
        byte[] bytes = arquivos.getFile().getBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        Part toIA = Part.fromBytes(arquivos.getFile().getBytes(), arquivos.getFile().getContentType());
        return DocumentContent.builder().data(base64).mimeType(DocumentContentMimeType.APPLICATION_PDF).build();
    }



    //Função dedicada apenas para receber o arquivo preparado pela IA
    public ResponseEntity<byte[]> geraDownload(@Param("file")  MultipartFile file) throws IOException {
        byte[] geraDoc = engine.processaDados(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Arquivo-resumido");
        return ResponseEntity.ok().headers(headers).body(geraDoc);
    }

}
