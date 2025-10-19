package calculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        int port = 5000;
        System.out.println("Lancement du serveur sur le port " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouveau client connecté : " + clientSocket.getInetAddress());
                ClientHandler handler = new ClientHandler(clientSocket);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
