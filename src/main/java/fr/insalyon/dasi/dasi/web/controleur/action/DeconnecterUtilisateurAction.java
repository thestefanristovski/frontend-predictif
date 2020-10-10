/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zineb
 */
public class DeconnecterUtilisateurAction extends Action{
    
        @Override
        public void executer(HttpServletRequest request) {
        
        
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idUtilisateur");
        if (id != null) {
            session.removeAttribute("idUtilisateur");
            session.invalidate();
        }

    }

    
}
