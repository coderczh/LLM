package com.coderczh.backend.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.coderczh.backend.common.Constant;
import com.coderczh.backend.service.DialogueService;
import io.github.pigmesh.ai.deepseek.core.DeepSeekClient;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionRequest;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import io.github.pigmesh.ai.deepseek.core.chat.ResponseFormatType;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class DialogueServiceImpl implements DialogueService {

    @Resource
    private DeepSeekClient deepSeekClient;

    @Override
    public Flux<ChatCompletionResponse> getAnswer(Integer model, String question) {
        if ((model != 0 && model != 1) || StrUtil.isBlank(question)) {
            return Flux.empty();
        } else {
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    // 添加用户输入的提示词（prompt），即模型生成文本的起点。告诉模型基于什么内容生成文本。
                    .addUserMessage(question)
                    // 指定使用的模型名称。不同模型可能有不同的能力和训练数据，选择合适的模型会影响生成结果。
                    .model(model == 0 ? Constant.MODEL_V3 : Constant.MODEL_R1)
                    // 是否以流式（streaming）方式返回结果。
                    .stream(true)
                    // 控制生成文本的随机性。0.0：生成结果非常确定，倾向于选择概率最高的词。1.0：生成结果更具随机性和创造性。
                    .temperature(0.7)
                    // 控制生成文本中重复内容的惩罚程度。0.0：不惩罚重复内容。1.0 或更高：减少重复内容，增加多样性。
                    .frequencyPenalty(0.5)
                    // 标识请求的用户。用于跟踪和日志记录，通常用于区分不同用户的请求。
                    .user("user")
                    // 控制生成文本时选择词的范围。0.7：从概率最高的 70% 的词中选择。1.0：不限制选择范围。
                    .topP(0.7)
                    // 控制模型生成的文本的最大长度。这对于防止生成过长的文本或确保响应在预期的范围内非常有用。
                    .maxCompletionTokens(2000)
                    // 响应结果的格式。
                    .responseFormat(ResponseFormatType.TEXT)
                    .build();
            return deepSeekClient.chatFluxCompletion(request);
        }
    }
}
