package org.example.Controller;

import org.example.Model.CalcadosModel;
import org.example.DAO.CalcadosDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


public class CalcadosController {
    private CalcadosDAO calcadosDAO;

    public CalcadosController(){
        calcadosDAO = new CalcadosDAO();
    }
    public void adicionarCalcado(float tamanho, String categoria, String cor, float preco, String marca, double dataCadastro, int qtdEstoque, String descricao, int calcadoId){
        CalcadosModel calcadosModel = new CalcadosModel(tamanho, categoria, cor, preco, marca, dataCadastro, qtdEstoque, descricao, calcadoId);
        CalcadosDAO calcadoDAO = new CalcadosDAO();
        calcadoDAO.adicionarCalcado(calcadosModel);
    }
    public CalcadosModel buscarCalcado(int idCalcado){
        return  calcadosDAO.buscarCalcadoPorId(idCalcado);
    }
    public void editarCalcado(int idCalcado, float novoTamanho, String novaCategoria, String novaCor, float novoPreco, String novaMarca, int novaQtdEstoque, String novaDescricao, int novoProdutoId){
        CalcadosModel calcadosModel = new CalcadosModel(idCalcado, novoTamanho, novaCategoria, novaCor, novoPreco, novaMarca, novaQtdEstoque, novaDescricao);
        calcadosDAO.adicionarCalcado(calcadosModel);
    }
    public void excluirCalcado(int idCalcado){
        calcadosDAO.excluirCalcado(idCalcado);
    }
}
