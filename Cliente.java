import java.io.*;
import java.net.*;

public class Cliente extends Conexion {
    public Cliente() throws IOException {
        super("cliente");
    }

    public void iniciarCliente() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Ingresa tu nombre: ");
            String username = consoleInput.readLine();

            // Hilo para enviar mensajes al servidor
            Thread sendThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while (true) {
                        clientMessage = consoleInput.readLine();
                        out.println(username + ": " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendThread.start(); // Iniciar el hilo de envío

            String serverMessage;

            while (true) {
                serverMessage = in.readLine();
                if (serverMessage == null) {
                    System.out.println("Servidor ha cerrado la conexión.");
                    break;
                }
                System.out.println(serverMessage);
            }

            sendThread.interrupt(); // Detener el hilo de envío
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
