import java.util.Scanner;

public class Programa {
    boolean rodando;
    Scanner scanner;
    // Seu primo possui muitos Livros, todos em papel, e vive
    //emprestando aos amigos dele. Entretanto, muitas vezes
    //ele esquece a quem emprestou e já perdeu diversos
    //Livros por causa disso. No almoço da família, no último
    //final de semana, ele pediu para você desenvolver um
    //programa para resolver este problema. Esse sistema
    //deve permitir o cadastro dos amigos, incluindo: nome,
    //número do telefone e e-mail e também cadastrar os
    //Livros, com: título, resumo, autor, personagem principal,
    //gênero (comédia, romance ou aventura, etc) e faixa
    //etária. Deve ser possível cadastrar os empréstimos e
    //obter a lista de Livros emprestados incluindo quais
    //amigos estão com quais Livros.

    public void evaluateChoice(int escolha){
        switch (escolha){
            case 1:
                System.out.println("opçao 1 escolhida");
                break;
            case 2:
                System.out.println("opçao 2 escolhida");
                break;
            case 3:
                System.out.println("opçao 3 escolhida");
                break;
            case 4:
                scanner.close();
                System.out.println("Programa desligará");
                rodando = false;
                break;
        }
    }

    public Programa(){
        this.rodando = true;
        scanner = new Scanner(System.in);
        while(rodando){
            System.out.println("Selecione a opção... \n(1)Cadastrar Amigo\t(2)Cadastrar Livro\n(3)Ver livros emprestados\t(4)Sair ");
            evaluateChoice(scanner.nextInt());
        }
    }

    public static void main(String[] args){
        Programa programa = new Programa();
    }

}
