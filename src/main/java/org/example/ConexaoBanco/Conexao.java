package org.example.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static final String URL = "jdbc:sqlserver://localhost:1433;database=Calcados;user=sa;password=Cazarine2003;trustServerCertificate=false; encrypt=false";
    private static final String USUARIO = "sa";
    private static final String SENHA = "Cazarine2003";

    public static Connection conectar(){
        Connection conexao = null;
        try{
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexao sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados");
        }
        return conexao;
    }
}
