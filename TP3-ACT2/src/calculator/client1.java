package calculator;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client1 {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int port = 5000;

        try (
            Socket socket = new Socket(serverIP, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.print("Entrez le premier nombre : ");
            double a = scanner.nextDouble();

            System.out.print("Entrez l'opérateur (+, -, *, /) : ");
            String operator = scanner.next();

            System.out.print("Entrez le deuxième nombre : ");
            double b = scanner.nextDouble();

            Operation op = new Operation(a, b, operator);
            out.writeObject(op);

            double result = (double) in.readObject();
            System.out.println("Résultat reçu du serveur : " + result);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}
