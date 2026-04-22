package Conciertos;

import java.sql.*;
import java.util.Scanner;
/**El main donde se encuentra un menu para usar los menus de otras clases que necesitemos usar. */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Creamos la conexión con la base de datos.
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())) {

            int opcion;

            do {
                //Creamos el menu donde cada opcion nos dejara usar un menu para cada opcion, para artista, conciertos o entradas.
                System.out.println("------------------------------");
                System.out.println("1. Menu de artistas");
                System.out.println("2. Menu de conciertos");
                System.out.println("3. Menu de entradas");
                System.out.println("4. Salir");
                System.out.println("------------------------------");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //En cada metodo le pasaremos nuestra variable scanner y la conexión a la base de datos.
                        MenuArtista.menuArtistas(sc, conn);
                        break;
                    case 2:
                        MenuConcierto.menuConciertos(sc, conn);
                        break;
                    case 3:
                        MenuEntrada.menuEntradas(sc, conn);
                        break;
                    case 4:
                        break;

                }

            } while (opcion != 4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

