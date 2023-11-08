import java.io.*;
import java.net.*;

public class Conexion {
    // Puerto
    final int port = 8080;
    final String host = "172.100.2.208"; // Dirección IP del servidor

    protected Socket socketCliente;
    protected ServerSocket socketServidor;

    Conexion(String tipo) throws IOException {
        if (tipo.equals("servidor")) {
            socketServidor = new ServerSocket(port);
            socketCliente = new Socket();
        } else {
            socketCliente = new Socket(host, port);
        }
    }
}
