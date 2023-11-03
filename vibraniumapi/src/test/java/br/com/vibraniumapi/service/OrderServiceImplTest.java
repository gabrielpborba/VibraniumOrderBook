package br.com.vibraniumapi.service;

import br.com.vibraniumapi.component.OrderQueueProducer;
import br.com.vibraniumapi.exceptions.InsufficientBalanceException;
import br.com.vibraniumapi.exceptions.InsufficientVibraniumException;
import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import br.com.vibraniumapi.model.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.Instant;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {
    @Mock
    private OrderService orderService;

    @Mock
    private OrderQueueProducer orderQueueProducer;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderBuySufficientBalance() throws JsonProcessingException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setType(OrderType.BUY);
        orderEntity.setPrice(100);
        orderEntity.setQuantity(10);
        orderEntity.setInstant(Instant.now());


        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setAmount(10000);
        orderEntity.setUser(userEntity);

        when(userService.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));

        assertDoesNotThrow(() -> orderService.create(orderEntity));
    }

    @Test
    void testCreateOrderSellSufficientVibranium() throws JsonProcessingException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setType(OrderType.SELL);
        orderEntity.setPrice(100);
        orderEntity.setQuantity(10);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setQuantity(1);
        userEntity.setQuantity(100);
        orderEntity.setUser(userEntity);


        when(userService.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));

        assertDoesNotThrow(() -> orderService.create(orderEntity));
    }
}
