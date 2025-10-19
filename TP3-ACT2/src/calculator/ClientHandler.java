package calculator;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            Operation operation = (Operation) in.readObject();
            double result = CalculatriceService.calculer(operation);
            out.writeObject(result);
            System.out.println("Opération traitée : " + operation.getA() + " " + operation.getOperator() + " " + operation.getB() + " = " + result);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur dans le thread client : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du socket client.");
            }
        }
    }
}
