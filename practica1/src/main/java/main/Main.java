package main;

import principal.*;
import principal.acciones.SeleccionaOpcionPrincipal;

import java.io.Serializable;

public class Main implements Serializable {
    public static void main(String[] args) {
        //creamos los objetos
        GestorClientes gestorClientes = new GestorClientes();
        GestorFacturas gestorFacturas = new GestorFacturas();
        BaseDeDatos baseDeDatos = new BaseDeDatos(gestorClientes, gestorFacturas);

        //mostramos el menu de opciones, leemos la opcion y lanzamos el metodo correspondiente
        SeleccionaOpcionPrincipal seleccionaOpcionPrincipal = new SeleccionaOpcionPrincipal(baseDeDatos);
        seleccionaOpcionPrincipal.ejecutaAccion(baseDeDatos);
    }
}