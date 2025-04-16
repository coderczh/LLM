package com.coderczh.backend.service;

import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import reactor.core.publisher.Flux;

public interface DialogueService {
    Flux<ChatCompletionResponse> getAnswer(Integer model, String question);
}
