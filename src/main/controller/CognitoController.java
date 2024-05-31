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

    public void SignUp(String nome, String email, String senha){
        System.out.println("Realizando Cadastro...");
        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(CLIENT_ID)
                .withUsername(email)
                .withPassword(senha);

        try {
            SignUpResult result = cognitoClient.signUp(signUpRequest);
            System.out.println("Cadastro realizado com sucesso.");
        } catch (InvalidPasswordException e) {
            System.out.println("Erro: Senha dever cumprir os requisitos:\n- Min 8 caracteres;\n- Letra Maiúscula;\n- Letra Minúscula;\n- Caracter especial. ");
        } catch (UsernameExistsException e) {
            System.out.println("Erro: Usuário já cadastrado. Efetue LOGIN para entrar");
        } catch (InvalidParameterException e) {
            System.out.println("Erro: Entradas inválidas.");
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um erro desconhecido.");
        }

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
            System.out.println("Login efetuado com sucesso.");
            return true;
        } catch (UserNotConfirmedException e) {
            System.out.println("Email ainda não foi confirmado.");
            return false;
        } catch (NotAuthorizedException e){
            System.out.println("Credenciais inválidas");
            return false;
        } catch (Exception e) {
            System.out.println("Erro inesperado. Tente novamente.");
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


