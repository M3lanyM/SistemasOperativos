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
import javax.swing.JOptionPane;

public class Cliente {

    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 8000;


    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             DataInputStream entrada = new DataInputStream(socket.getInputStream())) {
            System.out.println("Conectado al servidor Bingo");

            while (true) {
                String numero = entrada.readUTF();
                System.out.println("Número generado: " + numero);
            }
        } catch (IOException ex) {
            System.err.println("Error al conectarse al servidor: " + ex.getMessage());
        }
    }
}