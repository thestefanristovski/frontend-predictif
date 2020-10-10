/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import javax.servlet.http.HttpServletRequest;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zineb
 */
public class ProfilClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        Long id = (Long) session.getAttribute("idUtilisateur");
        Client client = service.rechercherClientParId(id);


        if (client != null) {
            request.setAttribute("client", client);
        }
    }

}