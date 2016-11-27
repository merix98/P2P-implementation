import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerTask implements Runnable {
    private int port;
    List<String> ip_already_connected;

    public ServerTask(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("Server Started on port " + port);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();

                if(checkForIp(clientSocket.getInetAddress().toString()))
                    new Thread(new ClientTask(clientSocket,this)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    boolean checkForIp(String ip){
        if(ip_already_connected.contains(ip)){
            return false;
        }else{
            ip_already_connected.add(ip);
        }
        return true;
    }
    public void done(String ip){
        ip_already_connected.remove(ip);
    }
}
