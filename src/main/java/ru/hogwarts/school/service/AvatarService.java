package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.IOException;
import java.util.List;

@Service
public interface AvatarService {

    Long uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar getAvatarById(Long studentId);
    List<Avatar> getAllAvatars (int pageNumber, int pageSize);

}
