package ru.hogwarts.school.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/avatar")
    public Page<Avatar> getAvatars(@RequestParam int page, @RequestParam int size) {
        return avatarService.getAvatars(page, size);
    }
}
