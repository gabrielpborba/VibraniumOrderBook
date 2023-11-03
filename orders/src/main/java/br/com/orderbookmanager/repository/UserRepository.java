package br.com.orderbookmanager.repository;


import br.com.orderbookmanager.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository  extends JpaRepository<UserEntity, Long> {

}
