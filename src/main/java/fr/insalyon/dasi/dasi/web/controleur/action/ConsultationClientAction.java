/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**

 */
public class ConsultationClientAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        
        Long idConsultation = Long.parseLong(request.getParameter("idConsultation"),10);
        Consultation consultation = service.rechercherConsultationParId(idConsultation);
        
        Client client  = consultation.getClient();
        
        
        if (client != null) {
            request.setAttribute("client", client);
        }
    }
}
