package client2;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234); // adapter IP réseau si besoin
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(in.readLine()); // Affiche le numéro du client
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = clavier.readLine()) != null) {
                out.println(msg);
                System.out.println(in.readLine());
            }
            in.close();
            out.close();
            socket.close();
            clavier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
