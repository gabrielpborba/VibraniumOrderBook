package br.com.vibraniumapi.repository;

import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByTypeAndQuantityGreaterThanOrderByPriceDesc(OrderType orderType, int quantity);
    List<OrderEntity> findAllByTypeAndQuantityGreaterThanOrderByPriceAsc(OrderType orderType, int quantity);

}
