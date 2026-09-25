package com.api.jered.ai.api.Tool;

import dev.langchain4j.service.SystemMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SystemMessage("Realize um resumo desse texto para mim")
public interface Prompt {
    void Executar(MultipartFile file) throws Exception;
}
