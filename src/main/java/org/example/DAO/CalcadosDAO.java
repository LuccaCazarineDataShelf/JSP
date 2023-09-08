package org.example.DAO;

import org.example.ConexaoBanco.Conexao;
import org.example.Model.CalcadosModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Repository
public class CalcadosDAO {
    private Connection conexao;

    public CalcadosDAO(){
        conexao = Conexao.conectar();
    }
    public void adicionarCalcado(CalcadosModel calcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "INSERT INTO DadosCalcados (tamanho, categoria, cor, preco, marca, dataCadastro, qtdEstoque, descricao, calcadoId)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setFloat(1, calcado.getTamanho());
                stmt.setString(2, calcado.getCategoria());
                stmt.setString(3, calcado.getCor());
                stmt.setFloat(4, calcado.getPreco());
                stmt.setString(5, calcado.getMarca());
                stmt.setDate(6, (Date) calcado.getDataCadastro());
                stmt.setInt(7, calcado.getQtdEstoque());
                stmt.setString(8, calcado.getDescricao());

                stmt.executeUpdate();
                System.out.println("Calçado adicionado com sucesso!");
            }
        }catch (SQLException e){
            System.err.println("Erro ao adicionar calçado: " + e.getMessage());
        }
    }

    public void editarCalcado(CalcadosModel calcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "UPDATE DadosCalcados SET tamanho = ?, categoria = ?, cor = ?, preco = ?," +
                    " marca = ?, dataCadastro = ?, qtdEstoque = ?, descricao = ? " +
                    "WHERE calcadoId = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setFloat(1, calcado.getTamanho());
                stmt.setString(2, calcado.getCategoria());
                stmt.setString(3, calcado.getCor());
                stmt.setFloat(4, calcado.getPreco());
                stmt.setString(5, calcado.getMarca());
                stmt.setDate(6, (Date) calcado.getDataCadastro());
                stmt.setInt(7, calcado.getQtdEstoque());
                stmt.setString(8, calcado.getDescricao());

                stmt.executeUpdate();

                System.out.println("Calçado editado com sucesso!");
            }
        } catch (SQLException e){
            System.err.println("Erro ao editar calçado: " + e.getMessage());
        }
    }

    public void excluirCalcado(int calcadoId){
        try(Connection conexao = Conexao.conectar()){
            String sql = "DELETE FROM DaadosCalcados where idCalcado = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, calcadoId);
                int linhasAfetadas = stmt.executeUpdate();
                if(linhasAfetadas > 0){
                    System.out.println("Calçado excluído com sucesso!");
                }else{
                    System.out.println("Nenhum calçado encontrado com esse ID.");
                }
            }
        } catch (SQLException e){
            System.err.println("Erro ao excluir o calçado: " + e.getMessage());
        }
    }

    public CalcadosModel buscarCalcadoPorId(int calcadoId){
        CalcadosModel calcado = null;

        try(Connection conexao = Conexao.conectar()){
            String sql = "SELECT * FROM DadosCalcados WHERE CalcadoId = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, calcadoId);
                try(ResultSet resultado = stmt.executeQuery()){
                    if(resultado.next()){
                        calcado = new CalcadosModel(
                                resultado.getFloat("tamanho"),
                                resultado.getString("categoria"),
                                resultado.getString("cor"),
                                resultado.getFloat("preco"),
                                resultado.getString("marca"),
                                resultado.getDate("dataCadastro"),
                                resultado.getInt("qtdEstoque"),
                                resultado.getString("descricao")
                        );
                    }
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar o calçado: " + e.getMessage());
        }
        return calcado;
    }
    public void filtrarCalcado(){
    }
}
