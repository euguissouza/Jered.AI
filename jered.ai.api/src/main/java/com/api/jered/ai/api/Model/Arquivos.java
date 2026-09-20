package com.api.jered.ai.api.Model;

import jakarta.persistence.Entity;

import java.nio.file.Path;


public class Arquivos {
    private String nome;
    private Path files;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Path getFiles() {
        return files;
    }

    public void setFiles(Path files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "Arquivos{" +
                "files=" + files +
                '}';
    }
}
