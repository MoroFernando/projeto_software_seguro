package main.controller;

import main.DAO.ContatoDAO;
import main.model.Contato;
import main.view.MenuView;

public class MenuController {
    private MenuView menuView;
    private ContatoDAO contatoDAO;

    public MenuController(){
        this.menuView = new MenuView();
        this.contatoDAO = new ContatoDAO();
        int escolha = -1;

        while(escolha != 0){
            escolha = menuView.opcoes();

            switch(escolha){
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                case 1:
                    menuView.exibeListaDeContatos(contatoDAO.listaContatos());
                    break;
                case 2:
                    contatoDAO.adicionaContato(menuView.adicionarContato());
                    break;
                case 3:
                    Contato contatoPraAtualizar = contatoDAO.buscarContatoPorId(menuView.pedeIdDoContato());
                    if(contatoPraAtualizar != null){
                        contatoDAO.atualizarContato(
                                menuView.atualizarContato(contatoPraAtualizar)
                        );
                    } else {
                        menuView.opcaoInvalida();
                    }
                    break;
                case 4:
                    Contato contatoPraRemover = contatoDAO.buscarContatoPorId(menuView.pedeIdDoContato());
                    if(contatoPraRemover != null){
                        contatoDAO.removerContato(contatoPraRemover);
                    } else {
                        menuView.opcaoInvalida();
                    }
                    break;
                default:
                    menuView.opcaoInvalida();
                    break;
            }
        }
    }
}

