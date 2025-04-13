package com.coderczh.backend.dto.dialogue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class DialogueInputDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5158155649656110596L;

    @NotBlank(message = "模型模式不能为空")
    @Pattern(regexp = "^Pro/deepseek-ai/DeepSeek-V3|Pro/deepseek-ai/DeepSeek-V3$", message = "模型模式错误")
    private String model;
    private Boolean stream;
    private Long maxTokens;
    private Double temperature;
    private Double topP;
    private Long topK;
    private Double frequencyPenalty;
    private Long n;
    private List<?> stop;

    @NotBlank(message = "请求体不能为空")
    private List<Message> messages;

    @Data
    private static class Message implements Serializable {
        @Serial
        private static final long serialVersionUID = 6670905981154577913L;

        @NotBlank(message = "请求角色不能为空")
        private String role;

        @NotBlank(message = "请求内容不能为空")
        private String content;
    }
}
