/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author souid
 */
public class CommencerConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        Long idConsultation = Long.parseLong(request.getParameter("idConsultation"),10);
        Long verification = service.commencerConsultation(idConsultation);

        request.setAttribute("verification", verification);
        
    }

    
}
