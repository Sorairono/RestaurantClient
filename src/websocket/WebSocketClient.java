package websocket;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class WebSocketClient {
	private Session session = null;
	private MessageHandler messageHandler;

	public WebSocketClient() throws javax.websocket.DeploymentException {
		URI uri;
		try {
			uri = new URI("ws://localhost:8080/WebSocket/server");
			try {
				ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@OnOpen
	public void handleOpen(Session session) {
		this.session = session;
		System.out.println("Connected to Server!");
	}

	@OnMessage
	public void handleMessage(String message) {
		System.out.println("Response from Server: " + message);
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	@OnClose
	public void handleClose() {
		System.out.println("Disconnected to Server!");
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

	public void addMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void sendMessage(String message) throws IOException {
		// this.session.getBasicRemote().sendText(message);
		this.session.getAsyncRemote().sendText(message);
	}

	public void disconnect() throws IOException {
		this.session.close();
	}

	public static interface MessageHandler {
		public void handleMessage(String message);
	}
}
