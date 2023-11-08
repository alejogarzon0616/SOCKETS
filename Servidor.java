import java.io.*;
import java.net.*;

public class Servidor extends Conexion {
    String mensaje;

    public Servidor() throws IOException {
        super("servidor");
    }

    public void iniciarServidor() {
        try {
            System.out.println("Servidor en ejecución en la dirección IP " + host + " en el puerto " + port);

            socketCliente = socketServidor.accept();
            System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);

            // Hilo para enviar mensajes al cliente
            Thread sendThread = new Thread(() -> {
                try {
                    String serverMessage;
                    BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        System.out.print("Servidor: ");
                        serverMessage = consoleInput.readLine();
                        out.println("Servidor: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendThread.start(); // Iniciar el hilo de envío

            while (true) {
                mensaje = in.readLine();
                if (mensaje == null) {
                    System.out.println("Cliente ha salido.");
                    break;
                }
                System.out.println(mensaje);
            }

            sendThread.interrupt(); // Detener el hilo de envío
            socketServidor.close();
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
