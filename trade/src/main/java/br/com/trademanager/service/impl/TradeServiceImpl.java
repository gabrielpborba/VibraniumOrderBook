package br.com.trademanager.service.impl;

import br.com.trademanager.component.UserQueueProducer;
import br.com.trademanager.repository.TradeRepository;
import br.com.trademanager.service.TradeService;
import br.com.trademanager.model.TradeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserQueueProducer userQueueProducer;
    public void create(TradeEntity trade) {

        try{
            trade.setInstant(Instant.now());
            tradeRepository.save(trade);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String message = objectMapper.writeValueAsString(trade);

            userQueueProducer.sendMessage(message);
            } catch (Exception e) {
            //colocar LOGGER ERR
                System.out.println(e.getMessage());
            }

    }

    public List<TradeEntity> findAll() {
        return tradeRepository.findAll();
    }
}

