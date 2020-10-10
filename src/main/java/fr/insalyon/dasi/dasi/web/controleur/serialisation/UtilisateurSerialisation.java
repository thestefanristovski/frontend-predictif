/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.serialisation;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Zineb
 */
public class UtilisateurSerialisation extends Serialisation {
        
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        
        
        JsonObject container = new JsonObject();

        Boolean connexion = (utilisateur!= null);

        container.addProperty("connexion", connexion);

        if (utilisateur != null) {
            JsonObject jsonUtilisateur = new JsonObject();
            jsonUtilisateur.addProperty("id", utilisateur.getId());
            
            if(utilisateur instanceof Client)
            {
               jsonUtilisateur.addProperty("type", "Client"); 
            } else
            {
               jsonUtilisateur.addProperty("type", "Employe"); 
            }

            container.add("utilisateur", jsonUtilisateur);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
