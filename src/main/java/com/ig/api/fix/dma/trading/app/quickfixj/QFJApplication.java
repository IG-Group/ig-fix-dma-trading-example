package com.ig.api.fix.dma.trading.app.quickfixj;

import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix42.MessageCracker;

@Slf4j
public class QFJApplication implements Application {

    private final MessageCracker messageCracker = new Fix42MessageCracker();
    private final NewOrderSender newOrderSender = new NewOrderSender();

    public QFJApplication() {
    }

    public void fromAdmin(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    public void fromApp(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        messageCracker.crack(message, sessionId);
    }

    public void onCreate(SessionID sessionId) {
        log.info("Session created {}", sessionId);
    }

    public void onLogon(SessionID sessionId) {
        log.info("Session successfully logged on [{}]", sessionId);
        log.info("Placing order");
        newOrderSender.sendNewOrder();
        log.info("Order sent");
    }

    public void onLogout(SessionID sessionId) {
        log.info("{} Logged out", sessionId);
    }

    public void toAdmin(Message message, SessionID sessionId) {
    }

    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
    }
}
