package com.api.jered.ai.api.Service;

import com.api.jered.ai.api.Model.Arquivos;
import dev.langchain4j.model.googleai.GeminiFiles;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AiEngine extends Arquivos{


    private final GeminiFiles filesApi;

    public AiEngine(GeminiFiles filesApi) {
        super();
        this.filesApi = filesApi;
    }

    public void uploadArquivo() throws IOException, InterruptedException {
        Path filesPath = Paths.get(getFiles().toUri());
        GeminiFiles.GeminiFile uploadFile = filesApi.uploadFile(filesPath, getNome());

    }


}
