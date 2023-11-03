package br.com.trademanager.repository;


import br.com.trademanager.model.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<TradeEntity, String> {

}
