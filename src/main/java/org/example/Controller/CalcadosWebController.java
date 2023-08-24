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

@Controller
public class CalcadosWebController {

    @RequestMapping(value = "/calcadosModel", method = RequestMethod.GET)
    public ModelAndView cliente(){
        return new ModelAndView("calcadosModel", "command", new CalcadosModel() );
    }
    @RequestMapping(value = "/addCalcadosModel", method = RequestMethod.POST )
    public String adicionarCalcadosModel(
            @ModelAttribute("SpringWeb") CalcadosModel calcadosModel, ModelMap model,
            HttpServletRequest request){

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                model.addAttribute("tamanho", calcadosModel.getTamanho());
                model.addAttribute("categoria", calcadosModel.getCategoria());
                model.addAttribute("cor", calcadosModel.getCor());
                model.addAttribute("preco", calcadosModel.getMarca());
                model.addAttribute("dataCadastro", calcadosModel.getDataCadastro());
                model.addAttribute("qtdEstoque", calcadosModel.getQtdEstoque());
                model.addAttribute("descricao", calcadosModel.getCalcadoId());
                model.addAttribute("calcadoId", calcadosModel.getCalcadoId());

                List<CalcadosModel> calcadosModels = (List<CalcadosModel>)
                request.getSession().getAttribute("caalcadosModel");
                if(calcadosModels == null){
                    calcadosModels = new ArrayList<CalcadosModel>();
                }
                calcadosModels.add(calcadosModel);
                request.getSession().setAttribute("calcadosModels", calcadosModels);

                return "exibeCalcadosModel";
    }
    @RequestMapping(value = "/listarCalcadosModel", method = RequestMethod.GET)
    public String listarCalcadoModels(
    @ModelAttribute("SpringWeb") ModelMap model, HttpServletRequest request){

        List<CalcadosModel> calcadosModels = (List<CalcadosModel>)
        request.getAttribute("calcadosModels");

        model.addAttribute("calcadosModels", calcadosModels);

        return "listarCalcadosModel";
    }
}
