package ex04_biblioteca;

import java.util.ArrayList;

public class Livro {
    private int codigo;
    private String titulo;
    private int ano;
    private Editora editora;
    private ArrayList<Autor> autores;
    private ArrayList<Capitulo> capitulos;

    public Livro(int codigo, String titulo, int ano, Editora editora, Autor autor, int numeroCapitulo, String tituloCapitulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.editora = editora;
        this.autores = new ArrayList<>();
        autores.add(autor);
        this.capitulos = new ArrayList<>();
        capitulos.add(new Capitulo(numeroCapitulo, tituloCapitulo));
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public Editora getEditora() {
        return editora;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }
    public void incluirAutor(Autor autor){
        if (!jahExiste(autor) && autor!=null){
            autores.add(autor);
        }
    }
    public void excluirAutor(Autor autor){
        if (autor!=null && jahExiste(autor)){
            autores.remove(autor);
        }
    }
    public boolean jahExiste(Autor autor){
        boolean existe = false;
        if(autor != null){
             for(Autor a : autores){
                if(a.getCodigo()==autor.getCodigo()){
                   existe = true;
                }  
            }
        }
        
        return existe;
    }

    public void incluirCapitulo(int numero, String tituloCapitulo){
        if(findCapituloByTitulo(tituloCapitulo)==null){
            capitulos.add(new Capitulo(numero, tituloCapitulo));
        }
    }
    public void excluirCapitulo(String tituloCapitulo){
        Capitulo capitulo = findCapituloByTitulo(tituloCapitulo);
        if(capitulo!=null){
            capitulos.remove(capitulo);
        }
    }
    public Capitulo findCapituloByTitulo(String capTitulo){
        Capitulo capitulo = null;
        for(Capitulo c : capitulos){
            if(c.getTitulo().equals(capTitulo)){
                capitulo = c;
            }
        }
        return capitulo;
    }
}
