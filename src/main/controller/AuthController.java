package main.controller;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import main.view.AuthView;

public class AuthController {
    private AuthView authView;
    private CognitoController cognitoController;
    private int escolha;
    private boolean autenticado;

    public AuthController(){
        cognitoController = new CognitoController();
        escolha = -1;
        authView = new AuthView();

        while(!autenticado){
            escolha = authView.opcoes();
            String[] credenciais;
            String email;
            String nome;
            String senha;

            switch (escolha){
                case 1:
                    credenciais = authView.getDadosLogin();
                    email = credenciais[0];
                    senha = credenciais[1];
                    autenticado = cognitoController.SignIn(email, senha);
                    break;
                case 2:
                    credenciais = authView.getDadosCadastro();
                    nome = credenciais[0];
                    email = credenciais[1];
                    senha = credenciais[2];
                    cognitoController.SignUp(nome, email, senha);
                    cognitoController.confirmEmail(email, authView.getCodigoConfirmacao(email));
                    break;
                case 3:
                    email = authView.getEmail();
                    cognitoController.confirmEmail(email, authView.getCodigoConfirmacao(email));
                    break;
                default:
                    authView.opcaoInvalida();
                    break;
            }
        }
    }
}
