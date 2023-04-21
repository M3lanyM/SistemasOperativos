/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingo2;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ValMendoza
 */
class Hilo extends Thread {
     private final Socket cliente;
    private final DataOutputStream salida;
    private final Set<Integer> numerosGenerados; // Conjunto para llevar registro de los números generados

    public Hilo(Socket cliente) throws IOException {
        this.cliente = cliente;
        this.salida = new DataOutputStream(cliente.getOutputStream());
        this.numerosGenerados = new HashSet<>(); // Inicializar el conjunto vacío
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Generar número aleatorio entre 1 y 75 que no haya sido generado previamente
                int numero;
                do {
                    numero = (int) (Math.random() * 75) + 1;
                } while (numerosGenerados.contains(numero));
                numerosGenerados.add(numero);

                // Enviar número generado a todos los clientes
                broadcast(String.valueOf(numero));

                // Escribir número generado en el archivo
                // Esperar un segundo antes de generar el siguiente número
                Thread.sleep(1000);

            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Error en el manejador de cliente: " + ex.getMessage());
        }
    }

    private synchronized void broadcast(String mensaje) throws IOException {
        System.out.println("Enviando número " + mensaje + " a todos los clientes");

        salida.writeUTF(mensaje);
        salida.flush();
    }
}



