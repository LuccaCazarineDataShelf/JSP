package org.example.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.Model.CalcadosModel;
import org.example.DAO.CalcadosDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void adicionarCalcado(@RequestBody Map<String, Object> payload){
        CalcadosModel calcadosModel = new CalcadosModel();
        calcadosModel.setTamanho(Float.parseFloat(payload.get("tamanho").toString()));
        calcadosModel.setCategoria(payload.get("categoria").toString());
        calcadosModel.setCor(payload.get("cor").toString());
        calcadosModel.setPreco(Float.parseFloat(payload.get("preco").toString()));
        calcadosModel.setMarca(payload.get("marca").toString());
        SimpleDateFormat dateFormatInput = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormatOutput = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dataCadastroUtil = dateFormatInput.parse(payload.get("dataCadastro").toString());
            String dataCadastroFormatted = dateFormatOutput.format(dataCadastroUtil);
            java.util.Date utilDate = dateFormatOutput.parse(dataCadastroFormatted);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            calcadosModel.setDataCadastro(sqlDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        calcadosModel.setQtdEstoque(Integer.parseInt(payload.get("qtdEstoque").toString()));
        calcadosModel.setDescricao(payload.get("descricao").toString());
        this.calcadosDAO.adicionarCalcado(calcadosModel);
        //return "adicionar";
    }

    @PostMapping("/editar/{calcadoIdAutoIncrement}")
    public String editarCalcado(@PathVariable int calcadoIdAutoIncrement, @RequestBody Map<String, Object> payload){

        CalcadosModel calcadoExistente = calcadosDAO.buscarCalcadoPorId(calcadoIdAutoIncrement);

        if(calcadoExistente == null){
            return "editar";
        }

       calcadoExistente.setTamanho(Float.parseFloat(payload.get("tamanho").toString()));
       calcadoExistente.setCategoria(payload.get("categoria").toString());
       calcadoExistente.setCor(payload.get("cor").toString());
       calcadoExistente.setPreco(Float.parseFloat(payload.get("preco").toString()));
       calcadoExistente.setMarca(payload.get("marca").toString());

       SimpleDateFormat dateFormatInput = new SimpleDateFormat("dd/MM/yyyy");
       SimpleDateFormat dateFormatOutput = new SimpleDateFormat("yyyy-MM-dd");
       try {
           Date dataCadastroUtil = dateFormatInput.parse(payload.get("dataCadastro").toString());
           String dataCadastroFormatted = dateFormatOutput.format(dataCadastroUtil);
           java.util.Date utilDate = dateFormatOutput.parse(dataCadastroFormatted);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           calcadoExistente.setDataCadastro(sqlDate);
       }catch (ParseException e){
           e.printStackTrace();
       }
       calcadoExistente.setQtdEstoque(Integer.parseInt(payload.get("qtdEstoque").toString()));
       calcadoExistente.setDescricao(payload.get("descricao").toString());

       calcadosDAO.editarCalcado(calcadoExistente);

       return "editarCalcado";
    }

    @PostMapping("/excluir/{calcadoIdAutoIncrement}")
    public String excluirCalcado(@PathVariable int calcadoIdAutoIncrement) {
        CalcadosModel calcadoExistente = calcadosDAO.buscarCalcadoPorId(calcadoIdAutoIncrement);
        if(calcadoExistente == null){
            return "excluir";
        }
        calcadosDAO.excluirCalcado(calcadoIdAutoIncrement);
        return "excluirCalcado";
    }

    @GetMapping("/filtrar")
    public List<CalcadosModel> filtrarCalcados(@RequestParam Map<String, String> filtros){
        List<CalcadosModel> calcadosFiltrados = calcadosDAO.filtrarCalcados(filtros);
        return calcadosFiltrados;
    }
}