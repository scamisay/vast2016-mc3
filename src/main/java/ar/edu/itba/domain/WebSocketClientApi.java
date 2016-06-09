package ar.edu.itba.domain;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * Created by scamisay on 08/06/16.
 */
public class WebSocketClientApi {

    public void connect(){
        WebSocketClient transport = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(transport);
        stompClient.setMessageConverter(new StringMessageConverter());
        //stompClient.setTaskScheduler(taskScheduler); // for heartbeats, receipts

        String url = "ws://127.0.0.1:8080/endpoint";
        //StompSessionHandler handler = ... ;
        //stompClient.connect(url, handler);
    }
}
