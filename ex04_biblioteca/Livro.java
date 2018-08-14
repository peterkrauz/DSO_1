package ex04_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int codigo;
    private String titulo;
    private int ano;
    private List<Autor> autores;
    private Editora editora;
    private List<Capitulo> capitulos;

    public Livro(){

    }

    public Livro(int codigo, String titulo, int ano, List<Autor> autores, Editora editora, int numeroCapitulo, String tituloCapitulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.autores = autores;
        this.editora = editora;
        capitulos = new ArrayList<>();
        capitulos.add(new Capitulo(numeroCapitulo, tituloCapitulo));
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public boolean jahVinculado(Autor autor) {
        return autores.contains(autor);
    }

    public void incluirAutor(Autor autor){
        if(autor != null && !jahVinculado(autor)){
            autores.add(autor);
        }
    }

    public void excluirAutor(Autor autor){
        if (autor != null && jahVinculado(autor)){
            autores.remove(autor);
        }
    }

    public boolean existeCapitulo(String tituloCapitulo) {
        boolean existe = false;
        for (Capitulo c : capitulos){
            if(c.getTitulo().equals(tituloCapitulo)){
                existe = true;
            }
        }
        return existe;
    }

    public void incluirCapitulo(int numero, String tituloCapitulo){
        if (existeCapitulo(tituloCapitulo)) {
            capitulos.add(new Capitulo(numero,tituloCapitulo));
        }
    }

    public void excluirCapitulo(String tituloCapitulo){
        if (!existeCapitulo(tituloCapitulo)) {
            capitulos.remove(findCapituloByTitulo(tituloCapitulo));
        }
    }

    public Capitulo findCapituloByTitulo(String capTitulo){
        Capitulo capitulo = null;
        for (Capitulo c : capitulos){
            if(c.getTitulo().equals(capTitulo)){
                capitulo = c;
            }
        }
        return capitulo;
    }

}
