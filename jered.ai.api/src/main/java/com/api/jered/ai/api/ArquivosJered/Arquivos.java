package com.api.jered.ai.api.ArquivosJered;

import org.springframework.web.multipart.MultipartFile;

public class Arquivos {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    @Override
    public String toString() {
        return "Arquivos{" +
                "file=" + file +
                '}';
    }
}
