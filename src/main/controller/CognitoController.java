package main.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;
import java.util.Map;

public class CognitoController {

    private String USER_POOL_ID;
    private String CLIENT_ID;
    private AWSCognitoIdentityProvider cognitoClient;

    public CognitoController(){
        USER_POOL_ID = "us-east-2_4ocpUj67B";
        CLIENT_ID = "57372hd9fqmufb28que5aod8ls";

        cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    }

    public boolean SignUp(String nome, String email, String senha){
        System.out.println("Realizando Cadastro...");
        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(CLIENT_ID)
                .withUsername(email)
                .withPassword(senha);

        try {
            SignUpResult result = cognitoClient.signUp(signUpRequest);
            System.out.println("\u001B[32mCadastro realizado com sucesso.\u001B[0m");
            return true;
        } catch (InvalidPasswordException e) {
            System.out.println("\u001B[31mErro: Senha dever cumprir os requisitos:\n- Min 8 caracteres;\n- Letra Maiúscula;\n- Letra Minúscula;\n- Caracter especial. \u001B[0m");
        } catch (UsernameExistsException e) {
            System.out.println("\u001B[31mErro: Usuário já cadastrado. Efetue LOGIN para entrar\u001B[0m");
        } catch (InvalidParameterException e) {
            System.out.println("\u001B[31mErro: Entradas inválidas.\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31mErro: Ocorreu um erro desconhecido.\u001B[0m");
        }
        return false;
    }

    public boolean SignIn(String email, String senha){
        System.out.println("Realizando Login...");

        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", senha);

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withAuthParameters(authParams)
                .withClientId(CLIENT_ID);

        try{
            InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);
            System.out.println("\u001B[32mLogin efetuado com sucesso.\u001B[0m");
            return true;
        } catch (UserNotConfirmedException e) {
            System.out.println("\u001B[31mEmail ainda não foi confirmado.\u001B[0m");
            return false;
        } catch (NotAuthorizedException e){
            System.out.println("\u001B[31mCredenciais inválidas\u001B[0m");
            return false;
        } catch (Exception e) {
            System.out.println("\u001B[31mErro inesperado. Tente novamente.\u001B[0m");
            return false;
        }
    }

    public void confirmEmail(String email, String code){
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withClientId(CLIENT_ID)
                .withUsername(email)
                .withConfirmationCode(code);

        try {
            ConfirmSignUpResult confirmSignUpResult = cognitoClient.confirmSignUp(confirmSignUpRequest);
            System.out.println("Email confirmado com sucesso.");
        } catch (CodeMismatchException e) {
            System.out.println("Código de confirmação inválido.");
        } catch (ExpiredCodeException e) {
            System.out.println("Código de confirmação expirado.");
        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro inesperado. Tente novamente.");
        }
    }

}


