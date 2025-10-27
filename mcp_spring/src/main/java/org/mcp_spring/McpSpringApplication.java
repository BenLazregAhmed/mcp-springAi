package org.mcp_spring;

import org.mcp_spring.tools.StockTools;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpSpringApplication.class, args);
    }
    @Bean
    public MethodToolCallbackProvider getMethodToolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(new StockTools())
                .build();
    }
}
