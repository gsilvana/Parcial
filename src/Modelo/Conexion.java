package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/seleccion";
    private static final String USUARIO = "silvana";
    private static final String CLAVE = "";

    private Connection conexion;

    public Conexion() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

    public int registrarFruta(String nombre, String fruta) {
        int resp = 0;
        try {
            Connection conn = getConexion();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO seleccion.parcial(Nombre, Frutas) VALUES (?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, fruta);

            resp = ps.executeUpdate(); // 1 correcto / 0 error
            System.out.println("Usuario registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return resp;
    }

    public List<String> obtenerFrutas() {
        List<String> frutas = new ArrayList<>();
        try {
            Connection conn = getConexion();
            PreparedStatement ps = conn.prepareStatement("SELECT Frutas FROM seleccion.parcial");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                frutas.add(rs.getString("Frutas"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener frutas: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
        return frutas;
    }
}