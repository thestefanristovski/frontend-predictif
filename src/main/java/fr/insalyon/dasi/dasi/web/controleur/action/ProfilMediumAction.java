/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefa
 */
public class ProfilMediumAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        
        Long idMedium = Long.parseLong(request.getParameter("idMedium"),10);
        
        
        Service service = new Service();
        Medium medium = service.rechercherMediumParId(idMedium);

        request.setAttribute("medium", medium);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();

    }
}
