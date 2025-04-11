import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Correo: " + correo;
    }
}

class Validador {
    public static boolean validarNombre(String nombre) {
        return nombre.matches("[A-Z][a-zA-Z ]{1,49}");
    }

    public static boolean validarCorreo(String correo) {
        String regex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, correo);
    }

    public static boolean validarContrasena(String contrasena) {
        return contrasena.matches("(?=.*[A-Z].*[A-Z])(?=.*[a-z].*[a-z].*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}");
    }
}

public class Main {
    private static final ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Sistema de Registro de Usuarios ===");

        while (true) {
            System.out.print("Ingrese su nombre (o escriba 'salir' para terminar): ");
            String nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("salir")) break;
            if (!Validador.validarNombre(nombre)) {
                System.out.println("Nombre inválido. Debe comenzar con mayúscula y contener solo letras.");
                continue;
            }

            System.out.print("Ingrese su correo electrónico: ");
            String correo = scanner.nextLine();
            if (!Validador.validarCorreo(correo)) {
                System.out.println("Correo inválido. Intente de nuevo.");
                continue;
            }

            System.out.print("Ingrese su contraseña: ");
            String contrasena = scanner.nextLine();
            if (!Validador.validarContrasena(contrasena)) {
                System.out.println("Contraseña inválida. Debe tener al menos 8 caracteres, 2 mayúsculas, 3 minúsculas, 1 número y 1 carácter especial.");
                continue;
            }

            Usuario nuevo = new Usuario(nombre, correo, contrasena);
            listaUsuarios.add(nuevo);
            System.out.println("Usuario registrado exitosamente.\n");
        }

        System.out.println("\nUsuarios registrados:");
        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }

        scanner.close();
    }
}
