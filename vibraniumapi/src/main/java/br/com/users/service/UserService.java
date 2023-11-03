package br.com.users.service;

import br.com.users.model.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();
    void create(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);
}