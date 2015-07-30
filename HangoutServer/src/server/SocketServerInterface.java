package server;

public interface SocketServerInterface {
	public boolean openConnection();
    public void handleSession();
    public void closeSession();
}
