package br.com.orderbookmanager.dto;

import br.com.orderbookmanager.model.TradeEntity;

public class TradeDto {
    private TradeEntity trade;

    public TradeDto() {

    }
    public TradeEntity getTrade() {
        return trade;
    }

    public void setTrade(TradeEntity trade) {
        this.trade = trade;
    }
}