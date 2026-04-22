package Conciertos;

import java.sql.*;
import java.util.Scanner;

/**Clase que sirve para usar el menu de concierto.*/
public class MenuConcierto {
    /**Metodo que sirve para usar el menu de conciertos, donde podemos añadir conciertos eliminarlos y que nos muestre los conciertos. */
    public static void menuConciertos(Scanner sc, Connection conn) {
        int opcion;

        do {
            //Creamos el menu para que el usuario pueda usar la opcion que quiera para añadir conciertos, eliminarlos o mostrarlos.

            System.out.println("------------------------------");
            System.out.println("1. Añadir conciertos");
            System.out.println("2. Eliminar conciertos");
            System.out.println("3. Listar conciertos");
            System.out.println("4. Salir del menu");
            System.out.println("------------------------------");
            opcion = sc.nextInt();

            switch (opcion) {

                //En esta opcion para registrar conciertos en la base de datos preguntando por los datos.
                case 1:
                    String sqlInsertar = "INSERT INTO CONCIERTO (ARTISTA_ID,FECHA,LUGAR,PRECIOENTRADA) VALUES (?,?,?,?)";
                    try (PreparedStatement pstm = conn.prepareStatement(sqlInsertar)) {
                        System.out.println("Dime el id del artista del concierto que quieras insertar");
                        int idArtista = sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(1, idArtista);
                        System.out.println("Dime la fecha(YYYY-MM-DDDD) del concierto que quieras insertar");
                        Date fecha = Date.valueOf(sc.nextLine());
                        pstm.setDate(2, fecha);
                        System.out.println("Dime el lugar del concierto que quieras insertar");
                        String lugar = sc.nextLine();
                        pstm.setString(3, lugar);
                        System.out.println("Dime el precio de la entrada que quieras insertar");
                        double precio = sc.nextDouble();
                        pstm.setDouble(4, precio);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                    //Esta opcion para eliminar conciertos de la base de datos por el ID.
                case 2:
                    String sqlEliminar = "DELETE FROM CONCIERTO WHERE ID=?";
                    try (PreparedStatement pstm = conn.prepareStatement(sqlEliminar)) {
                        System.out.println("Dime el id del concierto que quieras eliminar");
                        int id = sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(1, id);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    //Esta opcion para monstrar todos los conciertos que hay en la base de datos.
                    String sql = "SELECT ID,ARTISTA_ID,FECHA,LUGAR,PRECIOENTRADA FROM CONCIERTO";
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            int id = rs.getInt("ID");
                            int idArtista = rs.getInt("ARTISTA_ID");
                            Date fecha = rs.getDate("Fecha");
                            String lugar = rs.getString("lugar");
                            double precioEntrada = rs.getDouble("PRECIOENTRADA");
                            System.out.println(id + "- " + idArtista + " - " + fecha + " - " + lugar + " - " + precioEntrada);
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
