package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.IOException;

@Service
public interface AvatarService {

    Long uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar getAvatarById(Long studentId);
//    Avatar getAvatarById(Long id);
//    Avatar getAvatarById(Long studentId,Long avatarId);
}
