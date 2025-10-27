package org.mcp.mcp_client_spring.controller;

import org.mcp.mcp_client_spring.AiAgent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AiController {
    private AiAgent aiAgent;
    public AiController(AiAgent aiAgent) {
        this.aiAgent = aiAgent;
    }
    @GetMapping("/ask")
    public String askLLM(@RequestParam String message) {
        return aiAgent.askLLM(message);
    }
}
