package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatars.dir.path=avatars")
    private String avatarsDir;
    private StudentService studentService;
    private AvatarRepository avatarRepository;

//    public Long upLoadAvatar(Long studentId, MultipartFile file){
//        Student student = studentService.findStudentById(studentId);
//        return
//
//    }
}
