package br.com.orderbookmanager.repository;

import br.com.orderbookmanager.model.OrderEntity;
import br.com.orderbookmanager.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByPriceAndTypeAndAvailable(double price, OrderType type, boolean available);

}
