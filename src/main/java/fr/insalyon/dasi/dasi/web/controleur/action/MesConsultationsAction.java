/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 */
public class MesConsultationsAction extends Action{
    
    @Override
        public void executer(HttpServletRequest request) {
        
        
        HttpSession session = request.getSession();
        Long idEmploye = (Long) session.getAttribute("idUtilisateur");
        
        Service service = new Service();
        Employe employe = service.rechercherEmployeParId(idEmploye); // on rend l'employe en cours

        Consultation prochaineConsultation = service.obtenirProchaineConsultation(employe); // on regarde s'il a une prochaine consultation ou pas
        
        Client client = null;
        Medium medium = null;
        if(prochaineConsultation!=null) 
        {
            request.setAttribute("work", true);
            client = prochaineConsultation.getClient();
            medium = prochaineConsultation.getMedium();
            request.setAttribute("idConsultation", prochaineConsultation.getConsultationId());
            request.setAttribute("client", client);
            request.setAttribute("medium", medium);
        } else 
        {
            request.setAttribute("work", false);
        }
        
        }
}
