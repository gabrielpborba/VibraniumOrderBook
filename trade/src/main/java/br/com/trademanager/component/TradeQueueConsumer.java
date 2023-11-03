package br.com.trademanager.component;

import br.com.trademanager.dto.TradeDto;
import br.com.trademanager.service.TradeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TradeQueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TradeQueueConsumer.class);

    @Autowired
    private TradeService tradeService;

    @RabbitListener(queues = {"${rabbitmq.trade-queue.name}"})
    public void consume(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        TradeDto tradeDto = objectMapper.readValue(message, TradeDto.class);
        tradeDto.setInstant(Instant.now());

        logger.info("Received message from trade-queue: "+ message);


        try {
            tradeService.create(tradeDto.toTradeEntity());
        }catch (Exception e){
            logger.error("Error processing message: " + e.getMessage());
        }

    }
}


