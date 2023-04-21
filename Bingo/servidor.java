/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingo2;

/**
 *
 * @author ValMendoza
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class servidor {

    private static final int PUERTO = 8000;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor Bingo iniciado");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());

                Hilo hilo = new Hilo(cliente);
                hilo.start();
            }
        } catch (IOException ex) {
            System.err.println("Error al iniciar el servidor: " + ex.getMessage());
        }
    }
}
