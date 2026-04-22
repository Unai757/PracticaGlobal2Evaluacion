package Conciertos;

import java.sql.*;
import java.util.Scanner;
/**Clase para utilizar el menu de entrada.*/
public class MenuEntrada {
    /** Metodo para utilizar el menu de entradas donde podremos registrarlas o hacer una lista de las entradas.*/
    public static void menuEntradas(Scanner sc, Connection conn) {
        int opcion;
        //Creamos el menu para que se pueda usar la opcion que el usuario prefiera.
        do {
            System.out.println("------------------------------");
            System.out.println("1.Registrar entradas");
            System.out.println("2.Listar entradas");
            System.out.println("3.Salir del menu");
            System.out.println("------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                //Esta opcion para registrar entradas e insertarlas.
                case 1:
                    String sql = "INSERT INTO ENTRADA (concierto_id,comprador,cantidad,fechaCompra) VALUES(?,?,?,?)";
                    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                        System.out.println("Dime el id del concierto de esta entrada");
                        int idConcierto = sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(1, idConcierto);
                        System.out.println("Dime el nombre del comprador de esta entrada");
                        String nombre = sc.nextLine();
                        pstm.setString(2, nombre);
                        System.out.println("Dime la cantidad de entradas");
                        int cantidad = sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(3, cantidad);
                        System.out.println("Dime la fecha(YYYY-MM-DD) de la compra de esta entrada");
                        Date fechaCompra = Date.valueOf(sc.nextLine());
                        pstm.setDate(4, fechaCompra);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                    //Esta opcion para que nos muestre las entradas.
                case 2:
                    String sql2 = "SELECT ID,CONCIERTO_ID,COMPRADOR,CANTIDAD,FECHACOMPRA FROM ENTRADA";
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(sql2);
                        while (rs.next()) {
                            int id = rs.getInt("ID");
                            int idConcierto = rs.getInt("CONCIERTO_ID");
                            String comprador = rs.getString("COMPRADOR");
                            int cantidad = rs.getInt("CANTIDAD");
                            Date fechaCompra = rs.getDate("FECHACOMPRA");
                            System.out.println(id + " - " + idConcierto + " - " + comprador + " - " + cantidad + " - " + fechaCompra);
                        }


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    break;
            }
        } while (opcion != 3);
    }
}

