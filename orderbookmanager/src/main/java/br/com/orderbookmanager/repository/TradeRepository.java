package br.com.orderbookmanager.repository;


import br.com.orderbookmanager.model.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<TradeEntity, String> {


}
