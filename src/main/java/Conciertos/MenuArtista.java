package Conciertos;

import java.sql.*;
import java.util.Scanner;

/** Clase que nos sirve para usar el menu de artistas para añadir artistas eliminarlos o mostrarlos.*/
public class MenuArtista {

    /**Metodo que nos sirve para utilizar el menu de artista insertando eliminando o mostrándolos.*/
    public static void menuArtistas(Scanner sc, Connection conn) {
        int opcion;
        do {
            //Creamos el menu donde cada opcion nos dejara o añadir artista, eliminarlos o que nos muestre los artistas que hay.

            System.out.println("------------------------------");
            System.out.println("1. Añadir artistas");
            System.out.println("2. Eliminar artistas");
            System.out.println("3. Mostrar Artistas");
            System.out.println("4. Salir del menu");
            System.out.println("------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    //En este caso hacemos la opcion de insertar artistas con su nombre género y pais de origen el id no es necesario, ya que lo genera la tabla de por sí.
                    String sqlInsertar = "INSERT INTO artista (NOMBRE,GENEROMUSICAL,PAISORIGEN) VALUES (?, ?, ?)";
                    try (PreparedStatement pstm = conn.prepareStatement(sqlInsertar)) {
                        System.out.println("Dime el nombre del artista que quieras insertar");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        pstm.setString(1, nombre);
                        System.out.println("Dime el genero del artista que quieras insertar");
                        String genero = sc.nextLine();
                        pstm.setString(2, genero);
                        System.out.println("Dime el pais de origen del artista que quieras insertar");
                        String pais = sc.nextLine();
                        pstm.setString(3, pais);
                        pstm.executeUpdate();


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 2:
                    //En esta opcion se elimina un artista, eliminándolo usando el id.
                    String sqlEliminar = "DELETE FROM artista WHERE ID=?";
                    try (PreparedStatement pstm = conn.prepareStatement(sqlEliminar)) {
                        System.out.println("Dime el id del artista que quieras eliminar");
                        int id = sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(1, id);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    //Y en esta opcion mostramos todos los artistas
                    String sql = "SELECT ID,NOMBRE,GENEROMUSICAL,PAISORIGEN FROM artista";
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            int id = rs.getInt("ID");
                            String nombre = rs.getString("NOMBRE");
                            String genero = rs.getString("GENEROMUSICAL");
                            String pais = rs.getString("PAISORIGEN");
                            System.out.println(id + "- " + nombre + " - " + genero + " - " + pais);
                        }


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    break;
            }
        } while (opcion != 4);

    }
}