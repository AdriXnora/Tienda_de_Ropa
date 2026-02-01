package com.tienda.inventariotiendacoral;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Producto> inventario = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int contadorId = 1;

    public static void main(String[] args) {

        boolean salir = false;

        System.out.println("=== INICIAR APP ===");

        while (!salir) {

            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarProducto();
                    volverMenu();
                    break;

                case 2:
                    verProductos();
                    volverMenu();
                    break;

                case 3:
                    buscarProducto();
                    volverMenu();
                    break;

                case 4:
                    actualizarStock();
                    volverMenu();
                    break;

                case 5:
                    eliminarProducto();
                    volverMenu();
                    break;

                case 6:
                    salir = confirmarSalida();
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
                    volverMenu();
            }
        }

        System.out.println("=== FIN DEL PROGRAMA ===");
    }

    // ===== MENÚ =====
    static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Registrar producto");
        System.out.println("2. Ver productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Actualizar stock");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Salir");
    }

    // ===== OPCIÓN 1: REGISTRAR PRODUCTO =====
    static void registrarProducto() {

        System.out.println("\n--- REGISTRAR PRODUCTO ---");

        String nombre = leerTexto("Nombre: ");
        String marca = leerTexto("Marca: ");
        double precio = leerDouble("Precio: ");
        String tipoTela = leerTexto("Tipo de tela: ");
        String pais = leerTexto("País de fabricación: ");
        int stock = leerEnteroNoNegativo("Stock: ");

        Producto producto = new Producto(
                contadorId++, nombre, marca, precio, tipoTela, pais, stock
        );

        inventario.add(producto);

        System.out.println("✅ Producto guardado en BD (lista).");
    }

    // ===== OPCIÓN 2: VER PRODUCTOS =====
    static void verProductos() {

        System.out.println("\n--- INVENTARIO COMPLETO ---");

        if (inventario.isEmpty()) {
            System.out.println("📦 No hay productos registrados.");
            return;
        }

        for (Producto p : inventario) {
            System.out.println(p);
        }
    }

    // ===== OPCIÓN 3: BUSCAR PRODUCTO =====
    static void buscarProducto() {

        System.out.println("\n--- BUSCAR PRODUCTO ---");
        String busqueda = leerTexto("Ingrese nombre o marca: ");
        boolean existe = false;

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(busqueda) ||
                p.getMarca().equalsIgnoreCase(busqueda)) {

                System.out.println("\n✔ PRODUCTO ENCONTRADO:");
                System.out.println(p);
                existe = true;
            }
        }

        if (!existe) {
            System.out.println("❌ Producto NO encontrado.");
        }
    }

    // ===== OPCIÓN 4: ACTUALIZAR STOCK =====
    static void actualizarStock() {

        System.out.println("\n--- ACTUALIZAR STOCK ---");
        int id = leerEntero("Ingrese ID del producto: ");

        for (Producto p : inventario) {
            if (p.getId() == id) {

                int nuevoStock = leerEnteroNoNegativo("Ingrese nuevo stock: ");
                p.setStock(nuevoStock);

                System.out.println("✅ Stock actualizado en BD.");
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    // ===== OPCIÓN 5: ELIMINAR PRODUCTO =====
    static void eliminarProducto() {

        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        int id = leerEntero("Ingrese ID del producto: ");

        for (Producto p : inventario) {
            if (p.getId() == id) {

                System.out.println("\nProducto encontrado:");
                System.out.println(p);

                String confirmacion = leerTexto("¿Confirmar eliminación? (si/no): ");

                if (confirmacion.equalsIgnoreCase("si")) {
                    inventario.remove(p);
                    System.out.println("✅ Producto eliminado de BD.");
                } else {
                    System.out.println("❌ Eliminación cancelada.");
                }
                return;
            }
        }

        System.out.println("❌ Producto no encontrado.");
    }

    // ===== OPCIÓN 6: SALIR =====
    static boolean confirmarSalida() {

        System.out.println("\n--- SALIR ---");
        String respuesta = leerTexto("¿Desea salir del sistema? (si/no): ");

        if (respuesta.equalsIgnoreCase("si")) {
            return true; // FIN
        } else {
            System.out.println("↩ Volviendo al menú...");
            return false;
        }
    }

    // ===== VOLVER AL MENÚ =====
    static void volverMenu() {
        System.out.println("\nPresione ENTER para volver al menú...");
        sc.nextLine();
    }

    // ===== VALIDACIONES =====
    static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("❌ No puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número entero válido.");
            }
        }
    }

    static int leerEnteroNoNegativo(String mensaje) {
        int num;
        do {
            num = leerEntero(mensaje);
            if (num < 0) {
                System.out.println("❌ No se permiten valores negativos.");
            }
        } while (num < 0);
        return num;
    }

    static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número válido.");
            }
        }
    }
}
