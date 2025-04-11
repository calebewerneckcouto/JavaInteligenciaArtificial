package br.com.alura.ecomart.controller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.ModelType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cafe")
public class CategorizadorDeCafeController {

    private final ChatClient chatClient;

    public CategorizadorDeCafeController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4-turbo")
                        .build())
                .build();
    }

    @GetMapping
    public String categorizarCafe(String produto) {
        var system = """
                Você é um especialista em café e deve responder apenas o nome da categoria do café informado.

                Escolha uma categoria dentro da lista abaixo:

                1. Grãos
                2. Moído
                3. Cápsulas
                4. Solúvel
                5. Acessórios
                6. Outros

                ###### exemplo de uso:

                Pergunta: Café em grãos Arábica
                Resposta: Grãos

                Pergunta: Filtro de papel para café
                Resposta: Acessórios
                """;

        var tokens = contarTokens(system, produto);
        System.out.println("Total de Tokens: " + tokens);

        return this.chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(system)
                .user(produto)
                .options(ChatOptionsBuilder
                        .builder()
                        .withTemperature(0.8f)
                        .build())
                .call()
                .content();
    }

    public int contarTokens(String system, String user) {
        var registry = Encodings.newDefaultEncodingRegistry();
        var enc = registry.getEncodingForModel(ModelType.GPT_4_TURBO);
        return enc.countTokens(system + user);
    }
}