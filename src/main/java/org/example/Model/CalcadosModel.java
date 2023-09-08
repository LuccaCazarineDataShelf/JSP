package org.example.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcadosModel {

    public CalcadosModel(){
    }
    private float tamanho;
    private String categoria;
    private String cor;
    private float preco;
    private String marca;
    private Date dataCadastro;
    private int qtdEstoque;
    private String descricao;

    public void setDataCadastroString(String dataCadastroString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dataCadastro = dateFormat.parse(dataCadastroString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public CalcadosModel(float tamanho, String categoria, String cor, float preco, String marca, Date dataCadastro, int qtdEstoque, String descricao) {
        this.tamanho = tamanho;
        this.categoria = categoria;
        this.cor = cor;
        this.preco = preco;
        this.marca = marca;
        this.dataCadastro = dataCadastro;
        this.qtdEstoque = qtdEstoque;
        this.descricao = descricao;
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
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
}
