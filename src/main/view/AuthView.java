package main.view;

import main.util.InputValidator;

import java.util.Scanner;

public class AuthView {
    private Scanner sc;
    private InputValidator iv;

    public AuthView(){
        this.sc = new Scanner(System.in);
        this.iv = new InputValidator();
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
        String email = iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
        System.out.print("Senha: ");
        String senha = iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
        return new String[]{email, senha};
    }

    public String[] getDadosCadastro() {
        System.out.println("=== Registro ===");
        System.out.print("Nome: ");
        String nome = iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
        System.out.print("Email: ");
        String email = iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
        System.out.print("Senha: ");
        String senha = iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
        return new String[]{nome, email, senha};
    }

    public String getEmail(){
        System.out.print("Email: ");
        return iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
    }

    public String getCodigoConfirmacao(String email){
        System.out.print("Digite o cógido de verificação enviado para " + iv.validateString(email) + " : "); //IDS03-J. Do not log unsanitized user input
        return iv.validateString(sc.nextLine()); // IDS01-J. Normalize strings before validating them
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
