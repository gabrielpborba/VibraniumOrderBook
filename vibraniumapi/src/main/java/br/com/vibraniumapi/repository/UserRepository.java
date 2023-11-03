package br.com.vibraniumapi.repository;

import br.com.vibraniumapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
