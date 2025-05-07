package com.coderczh.backend.controller;

import com.alibaba.fastjson2.JSONObject;
import com.coderczh.backend.service.DialogueService;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import jakarta.annotation.Resource;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;

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


    public static String uploadToDeepSeek(File file) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(file, MediaType.parse("application/octet-stream")))
                .build();
        Request request = new Request.Builder()
                .url("https://api.deepseek.com/v1/files/upload")
                .header("Authorization", "Bearer YOUR_API_KEY")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string()).getString("file_id");
        }
    }

    // 提交提问时关联文件
    public static void askWithFile(String fileId, String question) {
        String jsonBody = String.format("{\"model\": \"deepseek-v3\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\", \"file_ids\": [\"%s\"]}]}", question, fileId);
        // 发送HTTP请求到DeepSeek问答接口
    }
}
