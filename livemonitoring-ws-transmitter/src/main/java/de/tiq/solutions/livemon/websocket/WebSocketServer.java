package de.tiq.solutions.livemon.websocket;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.shiro.subject.Subject;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServer
{
    
    @WebSocket
    public static class WsReceiverSocket
    {
        @OnWebSocketMessage
        public void onMessage( Session session, String message )
        {
        	System.out.println("message received");
            session.getRemote().sendStringByFuture(message);
        }
        @OnWebSocketConnect
        public void onOpen( final Session session )
        {
        	System.out.println("client connection open");
        	 Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        	Future<Void> sendStringByFuture = session.getRemote().sendStringByFuture("test");
        	try {
				sendStringByFuture.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
        }
    }
    

    @SuppressWarnings("serial")
    public static class WsReceiver extends WebSocketServlet
    {
        @Override
        public void configure( WebSocketServletFactory factory )
        {
        	factory.register(WsReceiverSocket.class);
        }
    }

}