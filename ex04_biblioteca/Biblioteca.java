package ex04_biblioteca;

import java.util.List;

public class Biblioteca {
    private List<Livro> livros;

    public void incluirLivro(Livro livro){
        if(!existeLivro(livro)){
            livros.remove(livro);
        }
    }

    public void excluirLivro(Livro livro){
        if(existeLivro(livro)){
            livros.remove(livro);
        }
    }

    public boolean existeLivro(Livro livro) {
        return livros.contains(livro);
    }

}


/*c) Os métodos incluirLivro e excluirLivro devem verificar caso o livro já esteja na biblioteca ou seja nulo.*/
