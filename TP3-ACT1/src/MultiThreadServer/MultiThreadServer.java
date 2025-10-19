package MultiThreadServer;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadServer {
    private static AtomicInteger clientCount = new AtomicInteger(1);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Serveur démarré sur le port 1234...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            int clientOrder = clientCount.getAndIncrement();
            System.out.println("Nouveau client connecté: "
                + clientSocket.getRemoteSocketAddress()
                + " | Ordre: " + clientOrder);

            new Thread(new ClientHandler(clientSocket, clientOrder)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int clientOrder;

    public ClientHandler(Socket clientSocket, int clientOrder) {
        this.clientSocket = clientSocket;
        this.clientOrder = clientOrder;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Votre ordre de connexion est : " + clientOrder);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
