package com.coderczh.backend.controller;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dialogue")
public class DialogueController {

    @Resource
    private OkHttpClient httpClient;

    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @SuppressWarnings("all")
    @SneakyThrows
    @PostMapping("/")
    public String getAnswer(@RequestParam("question") String question) {
        RequestBody requestBody = RequestBody.create("{\n  \"model\": \"Pro/deepseek-ai/DeepSeek-V3\",\n  \"stream\": false,\n  \"max_tokens\": 512,\n  \"temperature\": 0.7,\n  \"top_p\": 0.7,\n  \"top_k\": 50,\n  \"frequency_penalty\": 0.5,\n  \"n\": 1,\n  \"stop\": [],\n  \"messages\": [\n    {\n      \"role\": \"user\",\n      \"content\": \"明月几时有的下一句\"\n    }\n  ]\n}", CONTENT_TYPE);
        Request request = new Request.Builder()
                .url("https://api.siliconflow.cn/v1/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer sk-izxrtjnozdcvzttaqnrxdpphariewkiskaldqpitbcoafucr")
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        System.out.println(response.body().string());
        return null;
    }
}
