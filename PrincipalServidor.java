import java.io.IOException;

public class PrincipalServidor {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }
}
