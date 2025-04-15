package com.coderczh.backend.controller;

import com.coderczh.backend.dto.dialogue.DialogueInputDto;
import com.coderczh.backend.dto.dialogue.DialogueOutputDto;
import com.coderczh.backend.resp.ResultData;
import com.coderczh.backend.service.DialogueService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dialogue")
public class DialogueController {

    @Resource
    private DialogueService dialogueService;


    @PostMapping("/answer")
    public ResultData<DialogueOutputDto> getAnswer(@RequestBody DialogueInputDto dialogueInputDto) {
        return dialogueService.getAnswer(dialogueInputDto);
    }
}
