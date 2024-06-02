package main.DAO;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import main.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.out.println("\nContato adicionado com sucesso");
        } catch (MysqlDataTruncation e) {
            System.out.println("\u001B[31m\nErro: Um dos valores fornecidos é maior do que o permitido. Tente novamente.\u001B[0m");
        } catch (NullPointerException | SQLException e) {
            System.out.println("\u001B[31mErro ao se conectar com o banco de dados.\u001B[0m");
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

        } catch (NullPointerException | SQLException e) {
            System.out.println("\u001B[31mErro ao se conectar com o banco de dados.\u001B[0m");
        }

        return contatos;
    }

    public void atualizarContato(Contato contato){
        query = "UPDATE contatos SET nome = ?, email = ?, numero = ? WHERE id = ?;";
        try{
            ps = db.prepareStatement(query);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getNumero());
            ps.setInt(4, contato.getId());
            ps.executeUpdate();
            System.out.println("Contato atualizado");
        } catch (SQLException e) {
            System.out.println("\u001B[31m\nErro: Um dos valores fornecidos é maior do que o permitido. Tente novamente.\u001B[0m");
        } catch (NullPointerException e) {
            System.out.println("\u001B[31mErro ao se conectar com o banco de dados.\u001B[0m");
        }
    }

    public void removerContato(Contato contato){
        query = "DELETE FROM contatos WHERE id = ?;";
        try{
            ps = db.prepareStatement(query);
            ps.setInt(1, contato.getId());
            ps.executeUpdate();
            System.out.println("Contato removido");
        } catch (NullPointerException | SQLException e) {
            System.out.println("\u001B[31mErro ao se conectar com o banco de dados.\u001B[0m");
        }

    }

    public Contato buscarContatoPorId(int id) {
        query = "SELECT * FROM contatos WHERE id = ?;";
        Contato contatoEncontrado = null;

        try {
            ps = db.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                contatoEncontrado = new Contato();
                contatoEncontrado.setId(rs.getInt("id"));
                contatoEncontrado.setNome(rs.getString("nome"));
                contatoEncontrado.setEmail(rs.getString("email"));
                contatoEncontrado.setNumero(rs.getString("numero"));
            }
            ps.close();
        } catch (NullPointerException | SQLException e) {
            System.out.println("\u001B[31mErro ao se conectar com o banco de dados.\u001B[0m");
        }

        return contatoEncontrado;
    }

}
