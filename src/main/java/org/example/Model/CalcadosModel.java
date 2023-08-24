package org.example.Model;

import org.example.ConexaoBanco.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalcadosModel {

    public CalcadosModel(){
    }
    private float tamanho;
    private String categoria;
    private String cor;
    private float preco;
    private String marca;
    private double dataCadastro;
    private int qtdEstoque;
    private String descricao;
    private int calcadoId;


    public CalcadosModel(float tamanho, String categoria, String cor, float preco, String marca, double dataCadastro, int qtdEstoque, String descricao, int calcadoId) {
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.cor = cor;
        this.preco = preco;
        this.marca = marca;
        this.dataCadastro = dataCadastro;
        this.qtdEstoque = qtdEstoque;
        this.descricao = descricao;
        this.calcadoId = calcadoId;
    }

    public CalcadosModel(int idCalcado, float novoTamanho, String novaCategoria, String novaCor, float novoPreco, String novaMarca, int novaQtdEstoque, String novaDescricao) {
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(double dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCalcadoId() {
        return calcadoId;
    }

    public void setCalcadoId(int calcadoId) {
        this.calcadoId = calcadoId;
    }
}
