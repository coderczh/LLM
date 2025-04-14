package com.coderczh.backend.service.Impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.dto.dialogue.DialogueInputDto;
import com.coderczh.backend.dto.dialogue.DialogueOutputDto;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.service.DialogueService;
import jakarta.annotation.Resource;
import lombok.Cleanup;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class DialogueServiceImpl implements DialogueService {
    @Resource
    private OkHttpClient httpClient;

    private static final MediaType CONTENT_TYPE = MediaType.parse(Constant.CONTENT_TYPE);

    @Override
    public ResultData<DialogueOutputDto> getAnswer(DialogueInputDto dialogueInputDto) {
        JSONObject requestBody = getRequestBody(dialogueInputDto);
        @Cleanup Response response = send(requestBody);
        String answer = getAnswer(response);
        DialogueOutputDto dialogueOutputDto = new DialogueOutputDto()
                .setAnswer(answer)
                .setModel(dialogueInputDto.getModel());
        return ResultData.success(dialogueOutputDto);
    }

    private JSONObject getRequestBody(DialogueInputDto dialogueInputDto) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", dialogueInputDto.getModel() == 0 ? Constant.MODEL_V3 : Constant.MODEL_R1);
        requestBody.put("stream", false);
        requestBody.put("max_tokens", 512);
        requestBody.put("temperature", 0.7);
        requestBody.put("top_p", 0.7);
        requestBody.put("top_k", 50);
        requestBody.put("frequency_penalty", 0.5);
        requestBody.put("n", 1);
        requestBody.put("stop", new JSONArray());
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", dialogueInputDto.getQuestion());
        messages.add(message);
        requestBody.put("messages", messages);
        return requestBody;
    }

    @SneakyThrows
    private Response send(JSONObject requestBody) {
        RequestBody body = RequestBody.create(requestBody.toJSONString(), CONTENT_TYPE);
        Request request = new Request.Builder()
                .url(Constant.URL)
                .header("Content-Type", "application/json")
                .header("Authorization", Constant.API_KEY)
                .post(body)
                .build();
        return httpClient.newCall(request).execute();
    }

    @SneakyThrows
    private String getAnswer(Response response) {
        JSONObject responseBody = JSONObject.parse(response.body().string());
        JSONArray choices = JSONArray.from(responseBody.get("choices"));
        JSONObject choice = JSONObject.from(choices.get(0));
        JSONObject message = JSONObject.from(choice.get("message"));
        return (String) message.get("content");
    }
}
