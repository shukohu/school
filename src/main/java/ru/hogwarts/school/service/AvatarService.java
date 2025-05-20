package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

@Service
public class AvatarService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {

        this.avatarRepository = avatarRepository;
    }

    public Page<Avatar> getAvatars(int page, int size) {
        logger.info("Was invoked method to get avatars (page = {}, size ={}", page, size);
        if (page < 0){
            logger.warn("Incorrect page number: {}", page);
            page = 0;
        }
        if (size <= 0) {
            logger.warn("Invalid size : {}. default value 5", size);
            size = 5;
        }
        return avatarRepository.findAll(PageRequest.of(page, size));
    }
}
