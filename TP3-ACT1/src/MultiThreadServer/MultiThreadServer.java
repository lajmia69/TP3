package MultiThreadServer;
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadServer {
    // Pour numéroter les clients
    private static AtomicInteger compteurClients = new AtomicInteger(0);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur démarré sur le port 1234, en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int numeroClient = compteurClients.incrementAndGet();
                System.out.println("Client #" + numeroClient + " connecté depuis : " + clientSocket.getRemoteSocketAddress());
                // On démarre un thread dédié
                Thread clientThread = new Thread(new HandlerClient(clientSocket, numeroClient));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HandlerClient implements Runnable {
    private Socket socket;
    private int numClient;

    public HandlerClient(Socket socket, int numClient) {
        this.socket = socket;
        this.numClient = numClient;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Bienvenue ! Vous êtes le client numéro : " + numClient);
            // Optionnel : gérer la communication, par exemple un echo
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Client #" + numClient + " dit: " + line);
                out.println("Echo serveur: " + line);
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Client #" + numClient + " a provoqué une erreur: " + e.getMessage());
        }
    }
}
