package org.example.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.Model.CalcadosModel;
import org.example.DAO.CalcadosDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calcados")
public class CalcadosController {
    private CalcadosDAO calcadosDAO;

    public CalcadosController(){

    }

    @Autowired
    public CalcadosController(CalcadosDAO calcadosDAO) {
        this.calcadosDAO = calcadosDAO;
    }

    @PostMapping("/adicionar")
    public String adicionarCalcado(@RequestBody Map<String, Object> payload){
        CalcadosModel calcadosModel = new CalcadosModel();
        String parametroTam = payload.get("tamanho").toString();
        /*String parametroCat = payload.get("categoria").toString();
        String parametroCor = payload.get("cor").toString();
        String parametroPreco = payload.get("preco").toString();
        String parametroMarca = payload.get("marca").toString();
        String parametroDataCad = payload.get("dataCadastro").toString();
        String parametroQtd = payload.get("qtdEstoque").toString();
        String parametroDescricao = payload.get("descricao").toString();
        String parametroId = payload.get("calcadoId").toString();*/
        this.calcadosDAO.adicionarCalcado(calcadosModel);
        return "";
    }

    @PostMapping("/editar")
    public String editarCalcado(@RequestBody Map<String, Object> payload){
       int calcadoId = Integer.parseInt(payload.get("calcadoId").toString());
       CalcadosModel calcadoExistente = calcadosDAO.buscarCalcadoPorId(calcadoId);
       if(calcadoExistente == null){
           return "editar";
       }
       calcadoExistente.setTamanho(Float.parseFloat(payload.get("tamanho").toString()));
       calcadoExistente.setCategoria(payload.get("categoria").toString());
       calcadoExistente.setCor(payload.get("cor").toString());
       calcadoExistente.setPreco(Float.parseFloat(payload.get("preco").toString()));
       calcadoExistente.setMarca(payload.get("marca").toString());
       calcadoExistente.setDataCadastro(Double.parseDouble(payload.get("dataCadastro").toString()));
       calcadoExistente.setQtdEstoque(Integer.parseInt(payload.get("qtdEstoque").toString()));
       calcadoExistente.setDescricao(payload.get("descricao").toString());

       calcadosDAO.editarCalcado(calcadoExistente);

       return "editarCalcado";
    }

    @PostMapping("/excluir")
    public String excluirCalcado(@RequestBody Map<String, Object> payload) {
        int calcadoId = Integer.parseInt(payload.get("calcadoId").toString());
        CalcadosModel calcadoExistente = calcadosDAO.buscarCalcadoPorId(calcadoId);
        if(calcadoExistente == null){
            return "excluir";
        }
        calcadosDAO.excluirCalcado(calcadoId);
        return "excluirCalcado";
    }
    @GetMapping("/buscar/{idCalcado}")
    public String buscarCalcado(@PathVariable int idCalcado, Model model){
        CalcadosModel calcadosModel = calcadosDAO.buscarCalcadoPorId(idCalcado);
        if(calcadosModel != null){
            model.addAttribute("calcadoModel", calcadosModel);
            return "ExibeCalcado";
        }else{
            return "buscar";
        }
    }
}
