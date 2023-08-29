package org.example.Controller;

import org.example.Model.CalcadosModel;
import org.example.DAO.CalcadosDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/*@Controller
@RequestMapping("/calcados")*/
public class CalcadosController {
    private CalcadosDAO calcadosDAO;

    @Autowired
    public CalcadosController(CalcadosDAO calcadosDAO) {
        this.calcadosDAO = calcadosDAO;
    }

    @GetMapping("/adicionar")
    /*public void adicionarCalcado(float tamanho, String categoria, String cor, float preco, String marca, double dataCadastro, int qtdEstoque, String descricao, int calcadoId){
        CalcadosModel calcadosModel = new CalcadosModel(tamanho, categoria, cor, preco, marca, dataCadastro, qtdEstoque, descricao, calcadoId);
        CalcadosDAO calcadoDAO = new CalcadosDAO();
        calcadoDAO.adicionarCalcado(calcadosModel);*/
    public String adicionarCalcadoForm(Model model){
        model.addAttribute("calcado", new CalcadosModel());
        return "adicionarCalcadoForm";
    }
    @PostMapping("/adicionar")
    public String adicionarCalcado(@ModelAttribute CalcadosModel calcado){
        calcadosDAO.adicionarCalcado(calcado);
        return "redirect:/calcados/listar";
    }
    @GetMapping("/editar/{idCalcado}")
    public String editarCalcadoForm(@PathVariable int idCalcado, Model model){
        CalcadosModel calcado = calcadosDAO.buscarCalcadoPorId(idCalcado);
        model.addAttribute("calcado", calcado);
        return "editarCalcadoForm";
    }

    @RequestMapping("/editar/{idCalcado}")
    /*public CalcadosModel buscarCalcado(int idCalcado){
        return  calcadosDAO.buscarCalcadoPorId(idCalcado);
    }*/
    public String editarCalcado(@PathVariable int idCalcado, @ModelAttribute CalcadosModel calcado){
        calcadosDAO.editarCalcado(calcado);
        return "redirect:/calcados/listar";
    }
    /*
    public void editarCalcado(int idCalcado, float novoTamanho, String novaCategoria, String novaCor, float novoPreco, String novaMarca, int novaQtdEstoque, String novaDescricao, int novoProdutoId){
        CalcadosModel calcadosModel = new CalcadosModel(idCalcado, novoTamanho, novaCategoria, novaCor, novoPreco, novaMarca, novaQtdEstoque, novaDescricao);
        calcadosDAO.adicionarCalcado(calcadosModel);
    }*/
    @GetMapping("/excluir/{idCalcado}")
    public String excluirCalcado(@PathVariable int idCalcado) {
        calcadosDAO.excluirCalcado(idCalcado);
        return"redirect:/calcados/listar";
    }
    @GetMapping("/buscar/{idCalcado}")
    public String buscarCalcado(@PathVariable int idCalcado, Model model){
        CalcadosModel calcado = calcadosDAO.buscarCalcadoPorId(idCalcado);
        model.addAttribute("calcado", calcado);
        return "ExibeCalcado";
    }
}
