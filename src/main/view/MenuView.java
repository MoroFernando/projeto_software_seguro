package main.view;

import main.model.Contato;

import java.util.List;
import java.util.Scanner;

public class MenuView {
    private Scanner sc;

    public MenuView(){
        this.sc = new Scanner(System.in);
    }

    public int opcoes(){
        System.out.print("""
                
                *************************************
                *           MENU PRINCIPAL          *
                *************************************
                |                                   |
                | (1) Listar Contatos               |
                | (2) Criar Contato                 |
                | (3) Atualizar Contato             |
                | (4) Remover Contato               |
                |                                   |
                | (0) Sair                          |
                |___________________________________|
                
                ->\s""");
        int escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    public void exibeListaDeContatos(List<Contato> contatos){
        System.out.println("\n LISTA DE CONTATOS:");
        if(contatos != null){
            System.out.println(" ");
            System.out.println("+----+----------------------+---------------------------+-----------------+");
            System.out.println("| id | nome                 | email                     | numero          |");
            System.out.println("+----+----------------------+---------------------------+-----------------+");
            for (Contato contato : contatos) {
                System.out.printf("| %-2d | %-20s | %-25s | %-15s |%n",
                        contato.getId(),
                        contato.getNome(),
                        contato.getEmail(),
                        contato.getNumero());
            }
            System.out.println("+----+----------------------+---------------------------+-----------------+");
        } else {
            System.out.println(" ");
            System.out.println("Nenhum contato cadastrado...");
        }
        sc.nextLine();
    }

    public Contato adicionarContato(){
        String nome;
        String email;
        String numero;

        System.out.println("\n--> NOVO CONTATO");
        System.out.println(" ");

        System.out.print("Nome: ");
        nome = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Número: ");
        numero = sc.nextLine();

        Contato novoContato = new Contato();
        novoContato.setNome(nome);
        novoContato.setEmail(email);
        novoContato.setNumero(numero);

        return novoContato;
    }

    public Contato atualizarContato(Contato contato){
        String novoNome;
        String novoEmail;
        String novoNumero;

        System.out.println("\n--> ATUALIZAR CONTATO");
        System.out.println("+----+----------------------+---------------------------+-----------------+");
        System.out.printf("| %-2d | %-20s | %-25s | %-15s |%n",
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getNumero());
        System.out.println("+----+----------------------+---------------------------+-----------------+");
        System.out.println(" ");
        System.out.print("Novo Nome (vazio para manter o atual): ");
        novoNome = sc.nextLine();
        System.out.print("Novo Email (vazio para manter o atual): ");
        novoEmail = sc.nextLine();
        System.out.print("Novo Número (vazio para manter o atual): ");
        novoNumero = sc.nextLine();

        if(!novoNome.isEmpty()) {
            contato.setNome(novoNome);
        }
        if (!novoEmail.isEmpty()) {
            contato.setEmail(novoEmail);
        }
        if (!novoNumero.isEmpty()) {
            contato.setNumero(novoNumero);
        }

        return contato;
    }

    public int pedeIdDoContato(){
        int idContato;
        System.out.println("\n Insira o ID do contato: ");
        idContato = sc.nextInt();
        sc.nextLine();

        return idContato;
    }

    public void opcaoInvalida(){
        System.out.println("\u001B[31m\n** Opção Inválida **\u001B[0m");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
