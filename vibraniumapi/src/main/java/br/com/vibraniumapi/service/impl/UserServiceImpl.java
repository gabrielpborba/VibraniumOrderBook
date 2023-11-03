package br.com.vibraniumapi.service.impl;

import br.com.vibraniumapi.service.UserService;
import org.slf4j.Logger;
import br.com.vibraniumapi.model.UserEntity;
import br.com.vibraniumapi.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public void create(UserEntity userEntity) {

        try{
            userRepository.save(userEntity);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }

    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}

