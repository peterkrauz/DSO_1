import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    boolean rodando;
    Scanner scanner;
    Cadastrador cadastrador;
    List<Amigo> amigos;
    List<Livro> livros;
    List<Emprestimo> emprestimos;

    public void evaluateChoice(int escolha, Cadastrador cadastrador){
        switch (escolha){
            case 1:
                System.out.println("opçao 1 escolhida");
                amigos.add(cadastrador.cadastrarAmigo());
                break;
            case 2:
                System.out.println("opçao 2 escolhida");
                livros.add(cadastrador.cadastrarLivro());
                break;
            case 3:
                System.out.println("opçao 3 escolhida");

                System.out.println("Digite o nome do amigo...");
                String nomeAmigo = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Digite o titulo do livro...");
                String tituloLivro = scanner.nextLine();

                System.out.println("Digite a data de hoje...");
                String data = scanner.nextLine();

                Amigo amigo = acharAmigo(nomeAmigo);
                Livro livro = acharLivro(tituloLivro);
                emprestimos.add(cadastrador.cadastrarEmprestimo(amigo, livro, data));
                break;
            case 4:
                scanner.close();
                System.out.println("Programa desligará");
                rodando = false;
                break;
        }
    }

    private Livro acharLivro(String tituloLivro) {
        return new Livro();
    }

    private Amigo acharAmigo(String nomeAmigo) {
        return new Amigo();
    }

    public Programa(){
        this.rodando = true;
        scanner = new Scanner(System.in);
        Cadastrador cadastrador = new Cadastrador();
        amigos = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();

        while(rodando){
            System.out.println("Selecione a opção...\n(1)Cadastrar Amigo\t(2)Cadastrar Livro\n(3)Ver livros emprestados\t(4)Sair ");
            evaluateChoice(scanner.nextInt(), cadastrador);
        }
    }

    public static void main(String[] args){
        Programa programa = new Programa();
    }

}
