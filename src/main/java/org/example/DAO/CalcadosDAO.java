package org.example.DAO;

import org.example.ConexaoBanco.Conexao;
import org.example.Model.CalcadosModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class CalcadosDAO {
    private Connection conexao;

    public CalcadosDAO(){
        conexao = Conexao.conectar();
    }
    public void adicionarCalcado(CalcadosModel calcado){
        try(Connection conexao = Conexao.conectar()){
            String sql = "INSERT INTO DadosCalcados (tamanho, categoria, cor, preco, marca, dataCadastro, qtdEstoque, descricao)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
                    "WHERE calcadoIdAutoIncrement = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setFloat(1, calcado.getTamanho());
                stmt.setString(2, calcado.getCategoria());
                stmt.setString(3, calcado.getCor());
                stmt.setFloat(4, calcado.getPreco());
                stmt.setString(5, calcado.getMarca());
                stmt.setDate(6, (Date) calcado.getDataCadastro());
                stmt.setInt(7, calcado.getQtdEstoque());
                stmt.setString(8, calcado.getDescricao());
                stmt.setInt(9, calcado.getCalcadoIdAutoIncrement());

                stmt.executeUpdate();

                System.out.println("Calçado editado com sucesso!");
            }
        } catch (SQLException e){
            System.err.println("Erro ao editar calçado: " + e.getMessage());
        }
    }

    public void excluirCalcado(int calcadoId){
        try(Connection conexao = Conexao.conectar()){
            String sql = "DELETE FROM DadosCalcados where idCalcado = ?";
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

    public CalcadosModel buscarCalcadoPorId(int calcadoIdAutoIncrement){
        CalcadosModel calcado = null;

        try(Connection conexao = Conexao.conectar()){
            String sql = "SELECT * FROM DadosCalcados WHERE calcadoIdAutoIncrement = ?";

            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, calcadoIdAutoIncrement);
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
                                resultado.getString("descricao"),
                                resultado.getInt("calcadoIdAutoIncrement")
                        );
                    }
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar o calçado: " + e.getMessage());
        }
        return calcado;
    }
    public List <CalcadosModel> filtrarCalcados(Map<String, String> filtros){
        String sql = "SELECT * FROM DadosCalcados WHERE 1=1";

        if(filtros.containsKey("marca")){
            sql += "AND marca = '" + filtros.get("marca") + "'";
        }
        if(filtros.containsKey("tamanho")){
            sql += "AND tamanho " + filtros.get("tamanho");
        }
        if(filtros.containsKey("categoria")){
            sql += "AND categoria " + filtros.get("categoria");
        }
        if(filtros.containsKey("cor")){
            sql += "AND cor " + filtros.get("cor") ;
        }
        if(filtros.containsKey("precoMin")){
            sql += "AND preco >= " + filtros.get("precoMin");
        }
        if(filtros.containsKey("precoMax")){
            sql += "AND preco <= " + filtros.get("precoMax");
        }

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            List<CalcadosModel> calcadosFiltrados = new ArrayList<>();
            while (resultado.next()){
                CalcadosModel calcado = new CalcadosModel(
                        resultado.getFloat("tamanho"),
                        resultado.getString("categoria"),
                        resultado.getString("cor"),
                        resultado.getFloat("preco"),
                        resultado.getString("marca"),
                        resultado.getDate("dataCadastro"),
                        resultado.getInt("qtdEstoque"),
                        resultado.getString("descricao")
                );
                calcadosFiltrados.add(calcado);
            }
            return calcadosFiltrados;
        }catch (SQLException e ){
            System.err.println("Erro ao filtrar calcados: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
