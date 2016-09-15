package de.tiq.solutions.livemon.websocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketTransmitter
{
    
    @WebSocket
    public static class WsSenderSocket
    {
        @OnWebSocketMessage
        public void onMessage( Session session, String message )
        {
            try {
				session.getRemote().sendString("empfangen"+message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    

    @SuppressWarnings("serial")
    public static class WsSender extends WebSocketServlet
    {
        @Override
        public void configure( WebSocketServletFactory factory )
        {
        	factory.register(WsSenderSocket.class);
        }
    }

}