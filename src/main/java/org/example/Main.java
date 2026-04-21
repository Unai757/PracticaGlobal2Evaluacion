package org.example;

import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())) {

            int opcion;

            do {

                System.out.println("------------------------------");
                System.out.println("1. Menu de artistas");
                System.out.println("2. Menu de conciertos");
                System.out.println("3. Menu de entradas");
                System.out.println("4. Salir");
                System.out.println("----------------------------");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        menuArtistas(sc, conn);
                        break;
                    case 2:
                        menuConciertos(sc, conn);
                        break;
                    case 3:
                        menuEntradas(sc, conn);
                        break;
                    case 4:
                        break;

                }

            } while (opcion != 4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void menuArtistas(Scanner sc, Connection conn) {
        int opcion;
        do {

            System.out.println("------------------------------");
            System.out.println("1. Añadir artistas");
            System.out.println("2. Eliminar artistas");
            System.out.println("3. Mostrar Artistas");
            System.out.println("4. Salir del menu");
            System.out.println("-----------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
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

    public static void menuConciertos(Scanner sc, Connection conn) {
        int opcion;

        do {

            System.out.println("------------------------------");
            System.out.println("1. Añadir conciertos");
            System.out.println("2. Eliminar conciertos");
            System.out.println("3. Listar conciertos");
            System.out.println("4. Salir del menu");
            System.out.println("------------------------------");
            opcion = sc.nextInt();

            switch (opcion) {

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


    public static void menuEntradas(Scanner sc, Connection conn) {
        int opcion;
        do {
            System.out.println("------------------------------");
            System.out.println("1.Registrar entradas");
            System.out.println("2.Listar entradas");
            System.out.println("3. Salir del menu");
            System.out.println("----------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    String sql="INSERT INTO ENTRADA (concierto_id,comprador,cantidad,fechaCompra) VALUES(?,?,?,?)";
                    try(PreparedStatement pstm=conn.prepareStatement(sql)){
                        System.out.println("Dime el id del concierto de esta entrada");
                        int idConcierto=sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(1, idConcierto);
                        System.out.println("Dime el nombre del comprador de esta entrada");
                        String nombre=sc.nextLine();
                        pstm.setString(2, nombre);
                        System.out.println("Dime la cantidad de entradas");
                        int cantidad=sc.nextInt();
                        sc.nextLine();
                        pstm.setInt(3, cantidad);
                        System.out.println("Dime la fecha(YYYY-MM-DD) de la compra de esta entrada");
                        Date fechaCompra=Date.valueOf(sc.nextLine());
                        pstm.setDate(4, fechaCompra);
                        pstm.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    String sql2="SELECT ID,CONCIERTO_ID,COMPRADOR,CANTIDAD,FECHACOMPRA FROM ENTRADA";
                    try(Statement stmt=conn.createStatement()) {
                        ResultSet rs=stmt.executeQuery(sql2);
                            while (rs.next()) {
                                int id = rs.getInt("ID");
                                int idConcierto = rs.getInt("CONCIERTO_ID");
                                String comprador = rs.getString("COMPRADOR");
                                int cantidad = rs.getInt("CANTIDAD");
                                Date fechaCompra = rs.getDate("FECHACOMPRA");
                                System.out.println(id + " - "+ idConcierto+ " - " + comprador + " - " + cantidad + " - " + fechaCompra);
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

