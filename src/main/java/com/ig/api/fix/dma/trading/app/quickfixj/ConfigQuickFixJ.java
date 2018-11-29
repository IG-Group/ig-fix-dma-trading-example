package com.ig.api.fix.dma.trading.app.quickfixj;

import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.Application;

@Configuration
@EnableQuickFixJClient
public class ConfigQuickFixJ {

    /**
     * Provide QuickfixJ engine with an Application instance to receive and send FIX messages.
     * @return QuickFixJ callback class
     */
    @Bean
    public Application clientApplication() {
        return new QFJApplication();
    }

 }
