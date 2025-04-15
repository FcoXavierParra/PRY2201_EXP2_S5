/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp2_s5_francisco_parra;

/**@author fparraa*/
 

import java.util.Scanner;

public class Exp2_S5_Francisco_Parra {

  // Variables estáticas
    static int totalEntradasVendidas = 0;
    static String[][] carrito = new String[100][4]; // zona, precio base, descuento %, precio final
    static int ocupadosA = 0;
    static int ocupadosB = 0;
    static int ocupadosC = 0;
    static int disponiblesA = 30;
    static int disponiblesB = 35;
    static int disponiblesC = 35;
    static int capacidadSala = 100;
   

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuarPrograma = true;
        int indiceCarrito = 0;
        final double PRECIO_BASE = 10000;

        System.out.println("\n      BIENVENIDO AL TEATRO MORO     ");
        System.out.println("\n===== SISTEMA DE COMPRA DE ENTRADAS=====");
        
        mostrarResumenPreciosYDescuentos();

        while (continuarPrograma) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

                     switch (opcion) {
                case 1 -> indiceCarrito = comprarEntrada(scanner, indiceCarrito, PRECIO_BASE);
                case 2 -> buscarEntradas(scanner, indiceCarrito);
                case 3 -> {
                    finalizarCompra(indiceCarrito);
                    continuarPrograma = false;
                }
                case 4 -> indiceCarrito = eliminarEntrada(scanner, indiceCarrito);
                case 5 -> {
                    System.out.println("\nEsperamos verte pronto. Teatro Moro");
                    continuarPrograma = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Comprar entrada");
        System.out.println("2. Busqueda de entradas (Carrito)");
        System.out.println("3. Finalizar compra");
        System.out.println("4. Eliminar entrada");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción del Menu Principal: ");
    }

    public static int comprarEntrada(Scanner scanner, int indiceCarrito, double PRECIO_BASE) {
        
        System.out.println("\nZONAS DISPONIBLES: A.Premium, B.Media, C.Baja");
        mostrarEstadoAsientos();
        System.out.print("Ingrese la zona (A/B/C): ");
        String zona = scanner.nextLine().toUpperCase();

        double precioZona = 0;
        boolean zonaValida = true;

        switch (zona) {
            case "A":
                precioZona = PRECIO_BASE + 2000;
                break;
            case "B":
                precioZona = PRECIO_BASE;
                break;
            case "C":
                precioZona = PRECIO_BASE - 2000;
                break;
            default:
                System.out.println("Zona inválida.");
                zonaValida = false;
                break;
        }

        if (!zonaValida) {
            return indiceCarrito;
        }

        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();
        if (edad <= 0 || edad > 120) {
            System.out.println("Edad no válida.");
            return indiceCarrito;
        }

        System.out.print("¿Es estudiante? (s/n): ");
        String esEstudiante = scanner.next().toLowerCase();
        double descuento = 0;

        if (edad >= 60) {
            descuento = 0.15;
        } else if (esEstudiante.equals("s")) {
            descuento = 0.10;
        }

        double precioFinal = precioZona - (precioZona * descuento);

        carrito[indiceCarrito][0] = zona;
        carrito[indiceCarrito][1] = String.valueOf(precioZona);
        carrito[indiceCarrito][2] = String.valueOf(descuento * 100) + "%";
        carrito[indiceCarrito][3] = String.valueOf(precioFinal);

          switch (zona) {
            case "A" -> ocupadosA++;
            case "B" -> ocupadosB++;
            case "C" -> ocupadosC++;
        }

        totalEntradasVendidas++;
        System.out.println("\nEntrada agregada al carrito.");
        System.out.println("  Precio Zona :  $" + precioZona);
        System.out.println("  Descuento aplicado: " + (descuento * 100) + "%");
        System.out.println("  Precio final: $" + precioFinal);

        return indiceCarrito + 1;
    }

     public static void finalizarCompra(int indiceCarrito) {
        if (indiceCarrito == 0) {
            System.out.println("No hay entradas en el carrito.");
            return;
        }

        double totalPagar = 0;
        System.out.println("===== RESUMEN DE COMPRA  =====");

        for (int j = 0; j < indiceCarrito; j++) {
            if (carrito[j][0] != null) {
                System.out.println("Entrada " + (j + 1) + ": |  Zona: " + carrito[j][0]
                        + " | Precio Zona: $" + carrito[j][1]
                        + " | Descuento: " + carrito[j][2]
                        + " | Precio final: $" + carrito[j][3]);
                totalPagar += Double.parseDouble(carrito[j][3]);
            }
        }

        double descuentoExtra = 0;
        if (indiceCarrito >= 4) {
            descuentoExtra = 0.20; // 20% por 4 o más entradas 
        } else if (indiceCarrito >= 2) {
            descuentoExtra = 0.10; // 10% por 2 entradas
        }

        if (descuentoExtra > 0) {
            double descuentoAplicado = totalPagar * descuentoExtra;
            totalPagar -= descuentoAplicado;
            System.out.println("Descuento especial por compra múltiple: -" + (descuentoExtra * 100) + "% ($" + descuentoAplicado + ")");
        }

        System.out.println("Total a pagar: $" + totalPagar);
        System.out.println("¡Gracias por su compra! ¡Esperamos que vuelva pronto!");
    }

    public static int eliminarEntrada(Scanner scanner, int indiceCarrito) {

    if (indiceCarrito == 0) {
        System.out.println("\nNo hay entradas para eliminar.");
        return indiceCarrito;
    }

    System.out.println("\n===== CARRITO DE COMPRA =====");
    for (int j = 0; j < indiceCarrito; j++) {
        if (carrito[j][0] != null) {
            System.out.println("Entrada " + (j + 1) + ": |  Zona: " + carrito[j][0]
                    + " | Precio Zona: $" + carrito[j][1]
                    + " | Descuento: " + carrito[j][2]
                    + " | Precio final: $" + carrito[j][3]);
        }
    }

    System.out.print("Ingrese el número de entrada a eliminar (1 a " + indiceCarrito + "): ");
    int numeroEliminar = scanner.nextInt();
    scanner.nextLine(); // Limpiar buffer

    if (numeroEliminar < 1 || numeroEliminar > indiceCarrito || carrito[numeroEliminar - 1][0] == null) {
        System.out.println("Número inválido o entrada ya eliminada.");
        return indiceCarrito;
    }

    totalEntradasVendidas--;
    
    String zonaEliminada = carrito[numeroEliminar - 1][0];
    switch (zonaEliminada) {
        case "A" -> ocupadosA--;
        case "B" -> ocupadosB--;
        case "C" -> ocupadosC--;
    }

    for (int i = numeroEliminar - 1; i < indiceCarrito - 1; i++) {
        carrito[i] = carrito[i + 1];
    }

    carrito[indiceCarrito - 1] = new String[4]; // limpiar último

    System.out.println("Entrada eliminada con éxito.");

    indiceCarrito = indiceCarrito - 1;
    System.out.println("\n===== CARRITO DE COMPRA ACTUALIZADO =====");
    for (int j = 0; j < indiceCarrito; j++) {
        if (carrito[j][0] != null) {
            System.out.println("Entrada " + (j + 1) + ": |  Zona: " + carrito[j][0]
                    + " | Precio Zona: $" + carrito[j][1]
                    + " | Descuento: " + carrito[j][2]
                    + " | Precio final: $" + carrito[j][3]);
        }
    }
    return indiceCarrito; // reducir contador del carrito    
    
}
    public static void mostrarResumenPreciosYDescuentos() {
        System.out.println("\n===== RESUMEN DE PRECIOS =====");
        System.out.println("Zona A (Premium): $12000");
        System.out.println("Zona B (Media):   $10000");
        System.out.println("Zona C (Baja):    $8000");
        System.out.println("\n=====   DESCUENTOS =====");
        System.out.println("- Adulto mayor (60+): 15%");
        System.out.println("- Estudiantes:        10%");
        System.out.println("- Compra de 2 entradas: 10% adicional");
        System.out.println("- Compra de 4 o más entradas: 20% adicional");
    }

    public static void mostrarEstadoAsientos() {
        int disponiblesTotal = capacidadSala - (ocupadosA + ocupadosB + ocupadosC);
        System.out.println("\n===== DISPONIBILIDAD DE ASIENTOS =====");
        System.out.println("Capacidad Sala: " + capacidadSala + " | Total Ocupados: " + (ocupadosA + ocupadosB + ocupadosC));
        System.out.println("Zona A (Premium): Ocupados: " + ocupadosA + " | Disponibles: " + disponiblesA);
        System.out.println("Zona B (Media):   Ocupados: " + ocupadosB + " | Disponibles: " + disponiblesB);
        System.out.println("Zona C (Baja):    Ocupados: " + ocupadosC + " | Disponibles: " + disponiblesC);
    }

    public static void buscarEntradas(Scanner scanner, int indiceCarrito) {
        if (indiceCarrito == 0) {
            System.out.println("\nNo hay entradas registradas.");
            return;
        }

        System.out.println("\n===== BÚSQUEDA DE ENTRADAS =====");
        System.out.println("Buscar por:");
        System.out.println("1. Número de entrada");
        System.out.println("2. Zona (A/B/C)");
        System.out.println("3. Tipo de descuento (Estudiante/Tercera Edad)");
        System.out.println("4. Todo el Carrito");
        System.out.print("Seleccione una opción: ");
        int opcionBusqueda = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        boolean encontrada = false;

        switch (opcionBusqueda) {
            case 1 -> {
                System.out.print("Ingrese número de entrada: ");
                int numEntrada = scanner.nextInt();
                scanner.nextLine();
                if (numEntrada >= 1 && numEntrada <= indiceCarrito && carrito[numEntrada - 1][0] != null) {
                    System.out.println("Entrada " + numEntrada + ": | Zona: " + carrito[numEntrada - 1][0]
                            + " | Precio Zona: $" + carrito[numEntrada - 1][1]
                            + " | Descuento: " + carrito[numEntrada - 1][2]
                            + " | Precio final: $" + carrito[numEntrada - 1][3]);
                    encontrada = true;
                }
            }

            case 2 -> {
                System.out.print("Ingrese zona (A/B/C): ");
                String zona = scanner.nextLine().toUpperCase();
                for (int i = 0; i < indiceCarrito; i++) {
                    if (carrito[i][0] != null && carrito[i][0].equalsIgnoreCase(zona)) {
                        System.out.println("Entrada " + (i + 1) + ": | Zona: " + carrito[i][0]
                                + " | Precio Zona: $" + carrito[i][1]
                                + " | Descuento: " + carrito[i][2]
                                + " | Precio final: $" + carrito[i][3]);
                        encontrada = true;
                    }
                }
            }

            case 3 -> {
                System.out.print("Ingrese tipo de descuento (Estudiante/Tercera Edad): ");
                String tipo = scanner.nextLine().toLowerCase();
                String porcentaje = tipo.contains("tercera") ? "15.0%" : "10.0%";
                for (int i = 0; i < indiceCarrito; i++) {
                    if (carrito[i][0] != null && carrito[i][2].equals(porcentaje)) {
                        System.out.println("Entrada " + (i + 1) + ": | Zona: " + carrito[i][0]
                                + " | Precio Zona: $" + carrito[i][1]
                                + " | Descuento: " + carrito[i][2]
                                + " | Precio final: $" + carrito[i][3]);
                        encontrada = true;
                    }
                }
            }
            case 4 -> {
                System.out.println("\n===== CARRITO DE COMPRA ACTUALIZADO =====");
    for (int j = 0; j < indiceCarrito; j++) {
        if (carrito[j][0] != null) {
            System.out.println("Entrada " + (j + 1) + ": |  Zona: " + carrito[j][0]
                    + " | Precio Zona: $" + carrito[j][1]
                    + " | Descuento: " + carrito[j][2]
                    + " | Precio final: $" + carrito[j][3]);
        }
        encontrada = true;
    }
            }
            default -> System.out.println("Opción inválida.");
        }

        if (!encontrada) {
            System.out.println("No se encontraron entradas con los criterios ingresados.");
        }
 }    
}