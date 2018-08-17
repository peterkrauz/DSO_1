package ex04_biblioteca;

public class Capitulo {
    private int numero;
    private String titulo;

    public Capitulo(int numero, String titulo) {
        this.numero = numero;
        this.titulo = titulo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
