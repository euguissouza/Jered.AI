package com.api.jered.ai.api.Service;



import com.api.jered.ai.api.Tool.Prompt;


import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GeminiFiles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


@Service
public class AiEngine implements Prompt {

    private final GeminiFiles filesApi;
    private ChatModel chatModel;

    public AiEngine(GeminiFiles filesApi,  ChatModel chatModel) {
        this.filesApi = filesApi;
        this.chatModel = chatModel;
    }

    public Byte[] uploadArquivo(MultipartFile file) throws IOException, InterruptedException {

        Path arquivoTemporario = Files.createTempFile("upload", "-" + file.getOriginalFilename());
        try {
            file.transferTo(arquivoTemporario.toFile());
            GeminiFiles.GeminiFile uploadedFile = filesApi.uploadFile(
                    arquivoTemporario,
                    file.getOriginalFilename()
            );
            return new  Byte[0];
        } finally {
            Files.deleteIfExists(arquivoTemporario);
        }

    }
     public void ChatTerminal(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Chat: ");
         String input = sc.nextLine();
         ChatResponse response = chatModel.chat(ChatRequest.builder().messages(UserMessage.from(input)).build());
         String res = response.aiMessage().text();
         System.out.println("JERED: " + res);
     }


    @Override
    public void Executar(MultipartFile files) throws Exception {
        uploadArquivo(files);
    }

}
