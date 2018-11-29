ig-fix-dma-trading-example
==========

This is a example java application that sends an order to the IG FIX DMA Trading API.

The application can be run by a counter party who has been allocated a FIX DMA session. The FIX Comp IDs will require updating in 
_quickfixj-client.cfg_
and IG account values in 
_NewOrderSender.java_.

This example is written as a spring boot application.  The example can be run on command line by using the spring boot maven plugin e.g. 
 
 `mvn spring-boot:run`

Other ways of running a spring boot application can be found here 
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html


This example application is an initiator for the FIX connection. Once the session is logged on to IG's FIX API, it immediately sends a `NewOrderSingle` message. It is listening for response messages and once a ExecutionReport is received it will log the incoming message and log "Received execution report for client order: XYZ order status: X"  


