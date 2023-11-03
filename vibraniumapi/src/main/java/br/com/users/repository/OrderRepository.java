package br.com.users.repository;

import br.com.users.model.OrderEntity;
import br.com.users.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {



    List<OrderEntity> findAllByTypeAndQuantityGreaterThanOrderByPriceDesc(OrderType orderType, int quantity);
    List<OrderEntity> findAllByTypeAndQuantityGreaterThanOrderByPriceAsc(OrderType orderType, int quantity);

}
