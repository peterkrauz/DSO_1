import java.util.Date;
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

    public Emprestimo cadastrarEmprestimo(Amigo amigo, Livro livro, Date date){
        return new Emprestimo(livro, amigo, date);
    }

}
