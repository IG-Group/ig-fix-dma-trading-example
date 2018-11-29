package com.ig.api.fix.dma.trading.app.quickfixj;

import lombok.extern.slf4j.Slf4j;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SystemTime;
import quickfix.field.AllocAccount;
import quickfix.field.BeginString;
import quickfix.field.ClOrdID;
import quickfix.field.Currency;
import quickfix.field.HandlInst;
import quickfix.field.IDSource;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.SecurityExchange;
import quickfix.field.SecurityID;
import quickfix.field.SecurityIDSource;
import quickfix.field.SenderCompID;
import quickfix.field.SenderSubID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TargetCompID;
import quickfix.field.TimeInForce;
import quickfix.field.TransactTime;
import quickfix.fix42.NewOrderSingle;

import java.time.LocalDateTime;

@Slf4j
public class NewOrderSender {
    private final String senderSubId = "PZOUH";
    private final String igAccount = "PZOUH";

    private SessionID sessionID = new SessionID(new BeginString("FIX.4.2"),
            new SenderCompID("WINCHESTERTEST1"),
            new TargetCompID("IGAPI"));

    public void sendNewOrder() {
        NewOrderSingle nos = newOrderSingle();
        try {
            Session.sendToTarget(nos, sessionID);
        } catch (SessionNotFound sessionNotFound) {
            log.error("Session [{}] not found in qfj when sending order {}", sessionID, nos);
        }
    }

    private NewOrderSingle newOrderSingle() {
        NewOrderSingle nos = new NewOrderSingle();
        nos.getHeader().setField(new SenderSubID(senderSubId));
        nos.set(new ClOrdID(getNewClientOrderId()));
        nos.set(new OrdType(OrdType.MARKET));
        nos.set(new TimeInForce(TimeInForce.FILL_OR_KILL));
        nos.set(new Side(Side.BUY));
        nos.set(new OrderQty(101));
        nos.set(new IDSource(SecurityIDSource.ISIN_NUMBER));
        nos.set(new SecurityID("GB00BH4HKS39"));
        nos.set(new Currency("GBX"));
        nos.set(new Symbol("N/A"));
        nos.set(new SecurityExchange("L"));
        nos.set(new TransactTime(LocalDateTime.now()));
        nos.set(new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION));
        nos.addGroup(getAccountAllocation());
        nos.setString(8015, "4"); // order submitted by algorithm

        return nos;
    }

    private String getNewClientOrderId() {
        return String.valueOf(SystemTime.currentTimeMillis());
    }

    private NewOrderSingle.NoAllocs getAccountAllocation() {
        NewOrderSingle.NoAllocs allocs = new NewOrderSingle.NoAllocs();
        allocs.setString(AllocAccount.FIELD, igAccount);
        return allocs;
    }
}
