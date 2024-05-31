package main.DAO;

import main.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private Conexao conexao;
    private Connection db;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public ContatoDAO(){
        conexao = Conexao.getInstance();
        db = conexao.getDbConnection();
    }

    public void adicionaContato(Contato contato){
        query = "INSERT INTO contatos (nome, email, numero) VALUES (? , ?, ?);";
        try{
            ps = db.prepareStatement(query);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getNumero());
            ps.executeUpdate();
            System.out.println("Contato adicionado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contato> listaContatos(){
        List<Contato> contatos = new ArrayList<>();
        query = "SELECT * FROM contatos;";
        try{
            ps = db.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setNumero(rs.getString("numero"));
                contatos.add(contato);
            }

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contatos;
    }

    public void atualizaContato(Contato contato){
        query = "UPDATE contatos SET nome = ?, email = ?, numero = ? WHERE id = ?;";
        try{
            ps = db.prepareStatement(query);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getNumero());
            ps.setInt(4, contato.getId());
            ps.executeUpdate();
            System.out.println("Contato atualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerContato(Contato contato){
        query = "DELETE FROM contatos WHERE id = ?;";
        try{
            ps = db.prepareStatement(query);
            ps.setInt(1, contato.getId());
            ps.executeUpdate();
            System.out.println("Contato removido");
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
