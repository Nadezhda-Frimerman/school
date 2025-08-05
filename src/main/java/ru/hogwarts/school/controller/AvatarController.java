package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.AvatarServiceImpl;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private AvatarService avatarService;

//    @PostMapping("/{studentId}/upload", consumes=MediaType.MULTI)  {
//
//    }
}
