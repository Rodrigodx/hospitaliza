package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import com.rodrigo.hospitaliza.dtos.ChamadaEvent;

@Named
@ViewScoped
public class PainelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ChamadaEvent> historico = new ArrayList<>();
    
    private ChamadaEvent atual;

    public void processarChamadaSocket() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                                        .getExternalContext().getRequestParameterMap();
        
        String senha = params.get("senha");
        String paciente = params.get("paciente");
        String sala = params.get("sala");

        ChamadaEvent novaChamada = new ChamadaEvent(senha, paciente, sala);
        
        System.out.println();
        
        this.atual = novaChamada;

        this.historico.add(0, novaChamada);

        if (historico.size() > 6) {
            historico.remove(historico.size() - 1);
        }
    }

    public List<ChamadaEvent> getHistorico() { return historico; }
    public void setHistorico(List<ChamadaEvent> historico) { this.historico = historico; }
    
    public ChamadaEvent getAtual() { return atual; }
    public void setAtual(ChamadaEvent atual) { this.atual = atual; }
}