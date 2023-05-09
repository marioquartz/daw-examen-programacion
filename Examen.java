/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examen;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author marioquartz
 */
public class Examen {
    private Database database = new Database();
    private Scanner teclado = new Scanner(System.in);
    
    public Examen() {
        //Abrir conexión base de datos.
        this.database.openConnection();
    }

    public static void main(String[] args) {
        Gomez_maestre_mario_examen programa = new Gomez_maestre_mario_examen();

        //Mostrar nombre equipos
        programa.mostrarEquipos();
        
        //Mostrar ciudad en base al nombre de un jugador
        programa.mostrarCiudad();
        
        //Jugador mas alto de una división
        programa.jugadorMasAlto();
        
        //Media de la altura de un equipo
        programa.mediaEquipo();
        
        // Menu de la segunda parte (CRUD)
        programa.menu();
    }
    
    public void menu() {
        System.out.println("Opciones CRUD:");
        System.out.println("1/ Añadir jugador");
        System.out.println("2/ Eliminar jugador");
        System.out.println("3/ Convertir libras en kilos");
        System.out.println("\nElegir opción:");
        Integer opcion = this.teclado.nextInt();
        
        switch(opcion) {
            case 1:
                opcion1();
                menu();
                break;
            case 2:
                opcion2();
                menu();
                break;
            case 3:
                opcion3();
                menu();
                break;
        }
    }
    
    public void mostrarEquipos() {
        try {
            Statement statement = database.createStatement();
            ResultSet resultset = statement.executeQuery("select name from Teams");
            while(resultset.next()) {
                System.out.print(resultset.getString("name")+";");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void mostrarCiudad() {
        System.out.println("Introduce el nombre de un jugador del que desee saber la ciudad:");
        String nombre = this.teclado.nextLine();
        try {
            Statement statement = database.createStatement();
            ResultSet resultset = statement.executeQuery("select origin from Players WHERE NAME='"+nombre+"'");
            String ciudad = "";
            while(resultset.next()) {
                ciudad = resultset.getString("origin");
            }
            if (ciudad.equals("")) {
                System.out.println("El jugador no existe");
            } else {
                System.out.println("La ciudad de "+nombre+" es: "+ciudad);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void jugadorMasAlto() {
        System.out.println("Introduzca nombre de la División de la que desee saber el jugador mas alto:");
        String division = this.teclado.nextLine();
        
        try {
            Statement statement = database.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * from Players WHERE " +
            "nameIteam IN (SELECT NAME FROM Teams WHERE division='"+division+"') " +
            "ORDER BY height DESC LIMIT 0,1");
            while(resultset.next()) {
                System.out.println(resultset.getString("name")+" "+resultset.getString("height"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void mediaEquipo() {
        System.out.println("Introduzca equipo del que desee saber la altura media:");
        String equipo = this.teclado.nextLine();
        
        try {
            Statement statement = database.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT AVG(height) AS altura from Players WHERE nameIteam = '"+equipo+"'");
            while(resultset.next()) {
                System.out.println(resultset.getDouble("altura"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void opcion1() {
        System.out.println("Introduzca los siguientes datos:");
        teclado.nextLine();
        System.out.println("Nombre del Jugador:");
        String jugador_nombre=teclado.nextLine();
        System.out.println("Ciudad de origen:");
        String jugador_origen=teclado.nextLine();
        System.out.println("Altura:");
        Double jugador_altura=teclado.nextDouble();
        System.out.println("Peso:");
        Integer jugador_peso=teclado.nextInt();
        teclado.nextLine();
        System.out.println("Posición en el equipo:");
        String jugador_posicion=teclado.nextLine();
        System.out.println("Nombre del Equipo:");
        String jugador_equipo=teclado.nextLine();
        
        try {
            Statement statement = database.createStatement();
            String query = "INSERT INTO Players ( NAME, origin,height, weight, POSITION, nameIteam) values (";
            query+="'"+jugador_nombre+"',";
            query+="'"+jugador_origen+"',";
            query+="'"+jugador_altura+"',";
            query+="'"+jugador_peso+"',";
            query+="'"+jugador_posicion+"',";
            query+="'"+jugador_equipo+"');";
            //System.out.println(query);
            Integer cambio = statement.executeUpdate(query);
            if (cambio == 1) {
                System.out.println("Jugador añadido");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public void opcion2() {
        System.out.println("Introduzca el id del jugador que desee eliminar:");
        Integer id = teclado.nextInt();
        try {
            Statement statement = database.createStatement();
            Integer cambio = statement.executeUpdate("Delete FROM Players where id="+id);
            if (cambio == 0) {
                System.out.println("El id no existe.");
            } else {
                System.out.println("El jugador "+id+" ha sido eliminado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }
    
    public void opcion3() {
        System.out.println("Convertir libras en kilos");
        try {
            Statement statement = database.createStatement();
            Integer cambio = statement.executeUpdate("UPDATE Players set weight=weight*0.4536");
            System.out.println("Jugadores actualizados: "+cambio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
