package main.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Conexao conexao;
    private String url;
    private String usuario;
    private String senha;
    private Connection db;

    private Conexao(){
        conexao = null;
        this.url = "jdbc:mysql://localhost:3306/projeto_software_seguro";
        this.usuario = "root";
        this.senha = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db = (Connection) DriverManager.getConnection(url, usuario, senha);
            System.out.println(db);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Conexao getInstance(){
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getDbConnection(){
        return this.db;
    }
}
