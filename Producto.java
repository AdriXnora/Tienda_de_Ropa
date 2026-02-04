package com.tienda.inventariotiendacoral;

public class Producto {

    private int id;
    private String nombre;
    private String marca;
    private double precio;
    private String tipoTela;
    private String pais;
    private int stock;

    public Producto(int id, String nombre, String marca, double precio, String tipoTela, String pais, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.tipoTela = tipoTela;
        this.pais = pais;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipoTela() {
        return tipoTela;
    }

    public String getPais() {
        return pais;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        
        return nombre + " | Marca: " + marca + " | Precio: $" + precio +
               " | Tela: " + tipoTela + " | País: " + pais +
               " | Stock: " + stock;
    }
}
