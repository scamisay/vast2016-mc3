package ar.edu.itba.domain;

import ar.edu.itba.domain.exceptions.WSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by scamisay on 08/06/16.
 */
@Service
@ClientEndpoint
public class WSClient {

    private static final String token = "{\"uid\":\"scamisay@itba.edu.ar\",\"test\":true}";
    private static final String connectionURL = "ws://vast2016.labworks.org:80";

    private static Object waitLock = new Object();

    private PersistentEntity persistentEntity;

/*    public WSClient(){}*/

    @Autowired
    public WSClient(PersistentEntity persistentEntity) {
        this.persistentEntity = persistentEntity;
    }

    @OnMessage
    public void onMessage(String message) {
        persistentEntity.saveMessage(message);
    }

    public void startConsuming(){
        WebSocketContainer container=null;
        Session session=null;

        container = ContainerProvider.getWebSocketContainer();

        try {
            session=container.connectToServer(this, URI.create(connectionURL));
            session.getBasicRemote().sendText(token);
            wait4TerminateSignal();
        } catch (DeploymentException e) {
            throw new WSException(e.getMessage());
        } catch (IOException e) {
            throw new WSException(e.getMessage());
        }finally{
            if(session!=null){
                try {
                    session.close();
                } catch (Exception e) {
                    throw new WSException(e.getMessage());
                }
            }
        }


    }


    private static void  wait4TerminateSignal() {
        synchronized(waitLock) {
            try {
                waitLock.wait();
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        WebSocketContainer container=null;
        Session session=null;
        try{
            //Tyrus is plugged via ServiceLoader API. See notes above
            container = ContainerProvider.getWebSocketContainer();
            //WS1 is the context-root of my web.app
            //ratesrv is the  path given in the ServerEndPoint annotation on server implementation
            session=container.connectToServer(WSClient.class, URI.create(connectionURL));
            session.getBasicRemote().sendText(token);
            wait4TerminateSignal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(session!=null){
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
