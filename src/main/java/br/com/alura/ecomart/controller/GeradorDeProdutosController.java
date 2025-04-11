package br.com.alura.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gerador")
public class GeradorDeProdutosController {

    private final ChatClient chatClient;

    public GeradorDeProdutosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String gerarProdutos() {
        var pergunta = "Fale sobre  5 tipos de cafe";
        System.out.println("Enviando pergunta para o modelo de chat: " + pergunta); // Adicione este log

        String resposta = this.chatClient.prompt()
                .user(pergunta)
                .call()
                .content();

        System.out.println("Resposta recebida do modelo de chat: " + resposta); // Adicione este log
        return resposta;
    }

}
