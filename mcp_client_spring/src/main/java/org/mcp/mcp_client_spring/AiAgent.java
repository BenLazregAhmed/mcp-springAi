package org.mcp.mcp_client_spring;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AiAgent {
    private final ChatClient chatClient;

    public AiAgent(ChatClient.Builder chatClient, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultSystem("Answer the user question using provided tools")
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(MessageWindowChatMemory.builder().maxMessages(10).build()).build())
                .build();
    }

    public String askLLM(String query) {
        return chatClient.prompt()
                .user(query).call().content();
    }
}
