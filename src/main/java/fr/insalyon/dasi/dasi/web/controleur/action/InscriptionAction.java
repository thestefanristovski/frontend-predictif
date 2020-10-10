/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.EnumModele.Genre;
import static fr.insalyon.dasi.metier.modele.EnumModele.Genre.HOMME;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author souid
 */
public class InscriptionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        String dateNaissance = request.getParameter("dateNaissance");
        String gender = request.getParameter("gender");
        
        Genre genre= Genre.AUTRE;
        
        if(gender.equals("f"))
        {
            genre = Genre.FEMME;
        }
        if(gender.equals("h"))
        {
            genre = Genre.HOMME;
        }
        
        int year = Integer.parseInt(dateNaissance.substring(0, 3));
        int month = Integer.parseInt(dateNaissance.substring(5, 6));
        int day = Integer.parseInt(dateNaissance.substring(8, 9));
        
        Date date = new Date(year, month, day);

        Service service = new Service();
        Client client = new Client(nom, prenom, date, adresse, mail, telephone, mdp, genre);
        
        long idClient= -1;
        try {
            idClient = service.inscrireUtilisateur(client);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        client = service.rechercherClientParId(idClient);
        request.setAttribute("client", client);
        
        // Gestion de la Session: ici, enregistrer l'ID de l'utilisateur authentifié
        /*HttpSession session = request.getSession();
        if (service.rechercherClientParId(idClient) != null) {
            session.setAttribute("idClient", idClient);
        }
        else {
            session.removeAttribute("idClient");
        }*/ 
    }

    
}
