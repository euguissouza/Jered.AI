package com.api.jered.ai.api.Service;


import com.google.genai.Client;


import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.model.googleai.GeminiFiles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class AiEngine {


    private final GeminiFiles filesApi;

    public AiEngine(GeminiFiles filesApi) {
        this.filesApi = filesApi;
    }

    public Byte[] uploadArquivo(MultipartFile file) throws IOException, InterruptedException {

        Path arquivoTemporario = Files.createTempFile("upload", "-" + file.getOriginalFilename());
        try {
            file.transferTo(arquivoTemporario.toFile());
            GeminiFiles.GeminiFile uploadedFile = filesApi.uploadFile(
                    arquivoTemporario,
                    file.getOriginalFilename()
            );
        } finally {
            Files.deleteIfExists(arquivoTemporario);
        }
        return uploadArquivo(file);
    }

}
