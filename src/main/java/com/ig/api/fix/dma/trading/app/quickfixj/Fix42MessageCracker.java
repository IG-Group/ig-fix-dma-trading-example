package com.ig.api.fix.dma.trading.app.quickfixj;

import lombok.extern.slf4j.Slf4j;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix42.ExecutionReport;
import quickfix.fix42.MessageCracker;

@Slf4j
public class Fix42MessageCracker extends MessageCracker {

    @Override
    public void onMessage(ExecutionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        log.info("Received execution report for client order: {} order status: {}",
                message.getClOrdID().getValue(), message.getOrdStatus().getValue());
    }

}
