import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTask implements Runnable {
    private final Socket clientSocket;
    private final ServerTask server;
    public ClientTask(Socket clientSocket,ServerTask serverTask) {
        this.clientSocket = clientSocket;
        this.server = serverTask;
    }

    @Override
    public void run() {
        System.out.println("Got a client, " + clientSocket.getInetAddress());
        try{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = new Request();
        int resp = request.createRequestFromGETrequest(in);

        /*
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
        out.flush();
        out.close();
         */
        server.done(clientSocket.getInetAddress().toString());
        clientSocket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
