package main.view;

import java.util.Scanner;

public class AuthView {
    private Scanner sc;

    public AuthView(){
        this.sc = new Scanner(System.in);
    }

    public int opcoes(){
        System.out.print("""
                
                *************************************
                *            AUTENTICAÇÃO           *
                *************************************
                |                                   |
                | (1) Entrar                        |
                | (2) Cadastrar                     |
                | (3) Confirmar Email               |
                |                                   |
                |___________________________________|
                
                ->\s""");
        int escolha = sc.nextInt();
        sc.nextLine();
        return escolha;
    }

    public String[] getDadosLogin(){
        System.out.println("=== Login ===");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        return new String[]{email, senha};
    }

    public String[] getDadosCadastro() {
        System.out.println("=== Registro ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        return new String[]{nome, email, senha};
    }

    public String getEmail(){
        System.out.print("Email: ");
        return sc.nextLine();
    }

    public String getCodigoConfirmacao(String email){
        System.out.print("Digite o cógido de verificação enviado para " + email + " : ");
        return sc.nextLine();
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
