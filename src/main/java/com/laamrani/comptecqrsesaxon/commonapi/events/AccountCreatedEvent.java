package com.laamrani.comptecqrsesaxon.commonapi.events;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter private double initialeBalance;
    @Getter private String currency;
    public AccountCreatedEvent(String id, double initialeBalance, String currency) {
        super(id);
        this.initialeBalance = initialeBalance;
        this.currency = currency;
    }
}
