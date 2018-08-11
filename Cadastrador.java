import java.util.Scanner;

public class Cadastrador {
    Scanner scanner;
    //Deve ser possível cadastrar os empréstimos e
    //obter a lista de Livros emprestados incluindo quais
    //amigos estão com quais Livros.

    public Amigo cadastrarAmigo(){
        scanner = new Scanner(System.in);

        System.out.println("Digite o nome do novo amigo...");
        String nome = scanner.nextLine();
        System.out.println("Digite o email do novo amigo...");
        String email = scanner.nextLine();
        System.out.println("Digite o telefone do novo amigo...");
        String fone = scanner.nextLine();

        scanner.close();
        return new Amigo(nome, email, fone);
    }

    public Livro cadastrarLivro(){
        scanner = new Scanner(System.in);

        System.out.println("Digite o titulo do novo livro...");
        String titulo = scanner.nextLine();
        System.out.println("Digite o resumo do novo livro...");
        String resumo = scanner.nextLine();
        System.out.println("Digite o autor do novo livro...");
        Autor autor = new Autor(scanner.nextLine());
        System.out.println("Digite o personagemPrincipal do novo livro...");
        Pessoa personagemPrincipal = new Pessoa(scanner.nextLine());
        System.out.println("Digite o genero do novo livro...");
        String genero = scanner.nextLine();
        System.out.println("Digite o faixaEtaria do novo livro...");
        String faixaEtaria = scanner.nextLine();

        scanner.close();
        return new Livro(titulo, resumo, autor, personagemPrincipal, genero, faixaEtaria);
    }

    public Emprestimo cadastrarEmprestimo(Amigo amigo, Livro livro, String date){
        return new Emprestimo(livro, amigo, date);
    }

}
