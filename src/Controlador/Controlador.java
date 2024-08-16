package controlador;

import Modelo.Conexion;
import Vista.Lista;

public class Controlador {
    private Conexion conexion;
    private Lista vista;

    public Controlador(Lista vista) {
        this.vista = vista;
        this.conexion = new Conexion();
    }

    public void guardarSelección(String nombre, String fruta) {
        int respuesta = conexion.registrarFruta(nombre, fruta);
        if (respuesta == 1) {
            vista.mostrarMensaje("Selección guardada correctamente");
        } else {
            vista.mostrarMensaje("Error al guardar la selección");
        }
    }
}