/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author souid
 */
public class ProfilEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        Long id = (Long) session.getAttribute("idUtilisateur");
        Employe employe = service.rechercherEmployeParId(id);

        if (employe != null) {
            //System.out.println("employe != null");
            session.setAttribute("employe", employe);
        } else {
          //  System.out.println("employe = null");
            session.removeAttribute("employe");
        }
    }

}
