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
        calcadosModel.setTamanho(Float.parseFloat(payload.get("tamanho").toString()));
        calcadosModel.setCategoria(payload.get("categoria").toString());
        calcadosModel.setCor(payload.get("cor").toString());
        calcadosModel.setPreco(Float.parseFloat(payload.get("preco").toString()));
        calcadosModel.setMarca(payload.get("marca").toString());
        calcadosModel.setDataCadastro(payload.get("dataCadastro").toString());
        calcadosModel.setQtdEstoque(Integer.parseInt(payload.get("qtdEstoque").toString()));
        calcadosModel.setDescricao(payload.get("descricao").toString());
        calcadosModel.setCalcadoId(Integer.parseInt(payload.get("calcadoId").toString()));
        this.calcadosDAO.adicionarCalcado(calcadosModel);
        return "adicionar";
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
       calcadoExistente.setDataCadastro(payload.get("dataCadastro").toString());
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
