/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancier;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 */
public class ConsultationEnCoursSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long idConsultation = (Long)request.getAttribute("id");
        Service service = new Service();
        Consultation consultation = service.rechercherConsultationParId(idConsultation);
        JsonObject container = new JsonObject();
        
        Client c = consultation.getClient();
        Medium m = consultation.getMedium();
        
        JsonObject medium = new JsonObject();
        JsonObject client = new JsonObject();
        
        client.addProperty("nom", c.getNom());
        client.addProperty("prenom", c.getPrenom());
        client.addProperty("zodiac", c.getProfilAstral().getSigneZodiac());
        client.addProperty("chinois", c.getProfilAstral().getSigneChinois());
        client.addProperty("couleur", c.getProfilAstral().getCouleur());
        client.addProperty("animal", c.getProfilAstral().getAnimalTotem());
        
        medium.addProperty("denomination", m.getDenomination());
        medium.addProperty("presentation", m.getPresentation());
        if(m instanceof Spirite){
            //Spirite spirite=(Spirite)service.rechercherMediumParId(m.getIdMedium
            Spirite spirite = (Spirite) m;
            medium.addProperty("support", spirite.getSupport());
            medium.addProperty("type", "Spirite");
        }
        else if(m instanceof Cartomancier){
            //Cartomancier cartomancier=(Cartomancier)service.rechercherMediumParId(m.getIdMedium());
            Cartomancier cartomancier = (Cartomancier) m;
            medium.addProperty("type", "Cartomancier");
        }
        else if(m instanceof Astrologue){
           // Astrologue astrologue=(Astrologue)service.rechercherMediumParId(m.getIdMedium());
           Astrologue astrologue = (Astrologue) m;
            medium.addProperty("promotion", astrologue.getPromotion());
            medium.addProperty("formation", astrologue.getFormation());            
            medium.addProperty("type", "Astrologue");
        }
        
        container.add("medium", medium);
        container.add("client", client);
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }


}