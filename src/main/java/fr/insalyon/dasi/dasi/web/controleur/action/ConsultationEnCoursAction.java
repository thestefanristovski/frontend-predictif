/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 
 */
public class ConsultationEnCoursAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        
        long idConsultation = Long.parseLong(request.getParameter("id"), 16);
        Service service = new Service();
        Consultation c = service.rechercherConsultationParId(idConsultation);

        request.setAttribute("id", idConsultation);
    }

    
}
