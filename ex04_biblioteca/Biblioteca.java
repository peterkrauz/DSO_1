package ex04_biblioteca;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros = new ArrayList<>();

    public void incluirLivro(Livro livro){
        if(livro!=null && !existeLivro(livro)){
            livros.add(livro);
        }
    }

    public void excluirLivro(Livro livro){
        if(livro!=null && existeLivro(livro)){
            livros.remove(livro);
        }
    }

    public boolean existeLivro(Livro livro){
        return livros.contains(livro);
    }

}


