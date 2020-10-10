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
public class AuthentifierUtilisateurAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Service service = new Service();
        Utilisateur utilisateur = service.authentifierUtilisateur(login, password);

        request.setAttribute("utilisateur", utilisateur);
        
        // Gestion de la Session: ici, enregistrer l'ID de l'utilisateur authentifié
        HttpSession session = request.getSession();
        if (utilisateur != null) {
            session.setAttribute("idUtilisateur", utilisateur.getId());
        }
        else {
            session.removeAttribute("idUtilisateur");
        }
    }

    
}