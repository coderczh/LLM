package com.coderczh.backend.controller;

import com.coderczh.backend.service.DialogueService;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/dialogue")
public class DialogueController {

    @Resource
    private DialogueService dialogueService;

    @GetMapping("/answer/{model}/{question}")
    public Flux<ChatCompletionResponse> getAnswer(@PathVariable("model") Integer model,
                                                   @PathVariable("question") String question) {
        return dialogueService.getAnswer(model, question);
    }
}
