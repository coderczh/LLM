package com.coderczh.backend.dto.dialogue;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DialogueInputDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5158155649656110596L;

    @NotBlank(message = "内容不能为空")
    private String question;
    private Integer model;
}
