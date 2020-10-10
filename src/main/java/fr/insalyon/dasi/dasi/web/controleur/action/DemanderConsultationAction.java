/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 */
public class DemanderConsultationAction extends Action{
    
    @Override
        public void executer(HttpServletRequest request) {
        
        
        HttpSession session = request.getSession();
        Long idClient = (Long) session.getAttribute("idUtilisateur");
        Long idMedium = Long.parseLong(request.getParameter("idMedium"),10);
        
        Service service = new Service();
        Client client = service.rechercherClientParId(idClient);
        Medium medium = service.rechercherMediumParId(idMedium);
        
        Long idConsultation;
        idConsultation = service.creerDemande(client, medium);
        
        request.setAttribute("idConsultation", idConsultation);

        
        }
}
