package org.example.DAO;

import org.example.ConexaoBanco.Conexao;
import org.example.Model.CalcadosModel;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CalcadosDAO extends CalcadosModel {
    public void adicionarCalcado(CalcadosModel calcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "INSERT INTO Calcados (tamanho, categoria, cor, preco, marca, dataCadastro, qtdEstoque, descricao, calcadoId)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setFloat(1, getTamanho());
                stmt.setString(2, getCategoria());
                stmt.setString(3, getCor());
                stmt.setFloat(4, getPreco());
                stmt.setString(5, getMarca());
                stmt.setDouble(6, getDataCadastro());
                stmt.setInt(7, getQtdEstoque());
                stmt.setString(8, getDescricao());
                stmt.setInt(9, getCalcadoId());

                stmt.executeUpdate();
                System.out.println("Calçado adicionado com sucesso!");
            }
        }catch (SQLException e){
            System.err.println("Erro ao adicionar calçado: " + e.getMessage());
        }
    }

    public void editarCalcado(CalcadosModel calcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "UPDATE Calcados SET tamanho = ?, categoria = ?, cor = ?, preco = ?," +
                    " marca = ?, dataCadastro = ?, qtdEstoque = ?, descricao = ? " +
                    "WHERE idCalcado = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setFloat(1, getTamanho());
                stmt.setString(2, this.getCategoria());
                stmt.setString(3, this.getCor());
                stmt.setFloat(4, this.getPreco());
                stmt.setString(5, this.getMarca());
                stmt.setDouble(6, this.getDataCadastro());
                stmt.setInt(7, this.getQtdEstoque());
                stmt.setString(8, this.getDescricao());
                stmt.setInt(9, this.getCalcadoId());

                stmt.executeUpdate();

                System.out.println("Calçado editado com sucesso!");
            }
        } catch (SQLException e){
            System.err.println("Erro ao editar calçado: " + e.getMessage());
        }
    }
    public void excluirCalcado(int idCalcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "DELETE FROM Calcados where id = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, this.getCalcadoId());
                int linhasAfetadas = stmt.executeUpdate();
                if(linhasAfetadas > 0){
                    System.out.println("Calçado excluido com sucesso!");
                }else{
                    System.out.println("Nenhum calçado encontrado com esse id.");
                }
            }
        } catch (SQLException e){
            System.err.println("Erro ao excluir o calçado: " + e.getMessage());
        }
    }
    public CalcadosModel buscarCalcadoPorId(int idCalcado){
        CalcadosModel calcado = null;

        try(Connection conexao = Conexao.conectar()){
            String sql = "SELECT * FROM calcados WHERE idCalcado = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, this.getCalcadoId());
                try(ResultSet resultado = stmt.executeQuery()){
                    if(resultado.next()){
                        calcado = new CalcadosModel(
                                resultado.getFloat("tamanho"),
                                resultado.getString("categoria"),
                                resultado.getString("cor"),
                                resultado.getFloat("preço"),
                                resultado.getString("marca"),
                                resultado.getDouble("dataCadastro"),
                                resultado.getInt("qtdEstoque"),
                                resultado.getString("descrição"),
                                resultado.getInt("calcadoId")
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
