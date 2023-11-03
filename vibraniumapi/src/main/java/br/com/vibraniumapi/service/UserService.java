package br.com.vibraniumapi.service;

import br.com.vibraniumapi.model.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();
    void create(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);
}