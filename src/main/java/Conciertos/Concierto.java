package Conciertos;

import java.util.Date;

/**Clase artista con las variables de concierto.*/
public class Concierto {
    private int id;
    private int artista;

    private Date fecha;
    private String lugar;
    private double precioEntrada;

    public Concierto(String lugar, double precioEntrada, Date fecha, int artista, int id) {
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
        this.fecha = fecha;
        this.artista = artista;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public int getArtista() {
        return artista;
    }

    public void setArtista(int artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Concierto{" +
                "id=" + id +
                ", artista=" + artista +
                ", fecha='" + fecha + '\'' +
                ", lugar='" + lugar + '\'' +
                ", precioEntrada=" + precioEntrada +
                '}';
    }
}
