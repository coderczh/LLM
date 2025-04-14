package com.coderczh.backend.dto.dialogue;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DialogueOutputDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5158155649656110596L;

    private String answer;
    private Integer model;
}
