package com.tienda.inventariotiendacoral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static ArrayList<Producto> inventario = new ArrayList<>();
    public static HashMap<Integer, Producto> inventarioMap = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    public static int contadorId = 1;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }

    // REGISTRAR (usado por FormularioProducto) 
    public static void registrarProductoDesdeFormulario(
            String nombre, String marca, double precio,
            String tipoTela, String pais, int stock) {

        Producto producto = new Producto(
                contadorId++, nombre, marca, precio, tipoTela, pais, stock
        );

        inventario.add(producto);
        inventarioMap.put(producto.getId(), producto);
    }

    // VER PRODUCTOS 
    public static ArrayList<Producto> obtenerProductos() {
        return inventario;
    }

    // BUSCAR POR ID 
    public static Producto buscarPorId(int id) {
        return inventarioMap.get(id);
    }

    // BUSCAR POR NOMBRE O MARCA 
    public static ArrayList<Producto> buscarPorNombreOMarca(String texto) {
        ArrayList<Producto> resultados = new ArrayList<>();
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(texto) ||
                p.getMarca().equalsIgnoreCase(texto)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    // ACTUALIZAR STOCK 
    public static boolean actualizarStock(int id, int nuevoStock) {
        Producto p = inventarioMap.get(id); 
        if (p != null) {
            p.setStock(nuevoStock); 
            return true;
        }
        return false; 
    }

    // ELIMINAR PRODUCTO 
    public static boolean eliminarProducto(int id) {
        Producto p = inventarioMap.get(id);
        if (p != null) {
            inventario.remove(p);
            inventarioMap.remove(id);
            return true;
        }
        return false;
    }
    
    
public static boolean actualizarStockPorNombre(String nombreRecibido, int nuevoStock) {
    for (Producto p : inventario) {
        if (p.getNombre().equalsIgnoreCase(nombreRecibido)) {
            p.setStock(nuevoStock);
            return true;
        }
    }
    return false;
}

public static boolean eliminarProductoPorId(int id) {
   
    return inventario.removeIf(p -> p.getId() == id);
}
}
