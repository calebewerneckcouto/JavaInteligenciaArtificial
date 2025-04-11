# ☕ Ecomart - Projeto Café com Spring AI

Este é um projeto de demonstração que utiliza o **Spring Boot** e a biblioteca **Spring AI** para interagir com o modelo de linguagem da OpenAI. A aplicação expõe duas rotas REST para gerar conteúdos e categorizar produtos de café automaticamente.

## 📦 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.3
- Spring AI (`spring-ai-openai-spring-boot-starter`)
- OpenAI GPT-4 Turbo
- JTOKKit (contagem de tokens)
- Maven

---

## 🚀 Funcionalidades

### 🔹 `GET /gerador`

Endpoint responsável por gerar uma resposta a partir de uma pergunta enviada ao modelo de linguagem. No exemplo atual, a pergunta fixa é:

Fale sobre 5 tipos de café
O modelo retornará uma descrição para 5 tipos de café, utilizando o `ChatClient`.

---

### 🔸 `GET /cafe?produto=...`

Este endpoint classifica automaticamente o tipo de produto de café informado via query param.  
Exemplo:
/cafe?produto=Café em cápsulas intenso

O modelo retornará uma descrição para 5 tipos de café, utilizando o `ChatClient`.

---

### 🔸 `GET /cafe?produto=...`

Este endpoint classifica automaticamente o tipo de produto de café informado via query param.  
Exemplo:
/cafe?produto=Café em cápsulas intenso

**Resposta esperada:**
Cápsulas

O modelo considera as seguintes categorias:
- Grãos
- Moído
- Cápsulas
- Solúvel
- Acessórios
- Outros

---

## ⚙️ Configuração

Antes de rodar o projeto, defina a variável de ambiente com sua API key da OpenAI:

OPENAI_API_KEY=your-api-key-aqui

Ou configure diretamente no `application.properties` (não recomendado para produção):

```properties
spring.ai.openai.api-key=your-api-key-aqui
📌 Observações
A contagem de tokens é feita usando a biblioteca JTOKKit, útil para medir o custo e tamanho das mensagens enviadas ao modelo.

Os logs de envio e resposta podem ser acompanhados no console para depuração.
