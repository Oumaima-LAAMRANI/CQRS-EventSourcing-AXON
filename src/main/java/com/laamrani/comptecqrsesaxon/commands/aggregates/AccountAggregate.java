package com.laamrani.comptecqrsesaxon.commands.aggregates;

import com.laamrani.comptecqrsesaxon.commonapi.commands.CreateAccountCommand;
import com.laamrani.comptecqrsesaxon.commonapi.enums.AccountStatus;
import com.laamrani.comptecqrsesaxon.commonapi.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if(createAccountCommand.getInitialBalance()<0)
            throw new RuntimeException("Impossible de créer un compte avec un solde negatif");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getInitialeBalance();
        this.currency= event.getCurrency();
        this.status=AccountStatus.CREATED;
    }
}
