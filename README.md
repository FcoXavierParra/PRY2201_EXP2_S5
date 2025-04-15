# 🎭 Sistema de Compra de Entradas - Teatro Moro

Este proyecto es una aplicación de consola desarrollada en Java que simula un sistema de compra de entradas para el Teatro Moro. Permite al usuario seleccionar zonas, aplicar descuentos según su edad o si es estudiante, agregar entradas a un carrito, buscar o eliminar entradas, y finalizar la compra con resumen de precios.

## 📌 Funcionalidades

- Menú interactivo con opciones para:
  - Comprar entradas
  - Buscar entradas en el carrito
  - Finalizar la compra con cálculo de descuentos adicionales
  - Eliminar entradas del carrito
  - Salir del sistema
- Control de zonas con disponibilidad:
  - Zona A (Premium): $12.000
  - Zona B (Media): $10.000
  - Zona C (Baja): $8.000
- Descuentos disponibles:
  - 15% para adultos mayores (60+ años)
  - 10% para estudiantes
  - 10% adicional por la compra de 2 entradas
  - 20% adicional por la compra de 4 o más entradas
- Carrito de compras con capacidad de hasta 100 entradas

## 🧠 Lógica Principal

- El sistema valida zonas y edades al momento de comprar.
- Se calcula el precio final con descuentos individuales y grupales.
- La disponibilidad de entradas se actualiza dinámicamente.
- Se puede buscar en el carrito por número, zona o tipo de descuento.

## 🏗️ Estructura

- Archivo principal: `Exp2_S5_Francisco_Parra.java`
- Paquete: `com.mycompany.exp2_s5_francisco_parra`
- Uso de matriz bidimensional `carrito[100][4]` para almacenar entradas

## ▶️ Cómo Ejecutar

1. Abre el proyecto en NetBeans o tu IDE de preferencia.
2. Asegúrate de tener configurado un JDK (Java Development Kit).
3. Ejecuta el archivo `Exp2_S5_Francisco_Parra.java`.
4. Interactúa con el sistema desde la consola.

## 👨‍💻 Autor

Francisco Parra  
Proyecto de práctica – Experiencia 2, Semana 5

---

¡Gracias por usar el sistema del Teatro Moro!
