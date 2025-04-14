package com.coderczh.backend.service;

import com.coderczh.backend.dto.dialogue.DialogueInputDto;
import com.coderczh.backend.dto.dialogue.DialogueOutputDto;
import com.coderczh.backend.resp.ResultData;

public interface DialogueService {
    ResultData<DialogueOutputDto> getAnswer(DialogueInputDto dialogueInputDto);
}
