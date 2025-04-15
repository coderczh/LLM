package com.coderczh.backend.service;

import com.coderczh.backend.dto.dialogue.DialogueInputDto;
import com.coderczh.backend.dto.dialogue.DialogueOutputDto;
import com.coderczh.backend.resp.ResultData;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DialogueService {
    ResultData<DialogueOutputDto> getAnswer(@Valid DialogueInputDto dialogueInputDto);
}
