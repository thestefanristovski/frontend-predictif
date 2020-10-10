/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancier;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.EnumModele;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stefan
 */
public class MediumSerialisation extends Serialisation {
    
        @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        JsonObject container = new JsonObject();
        Medium medium = (Medium) request.getAttribute("medium");
      
        Boolean connexion = (medium != null);
        container.addProperty("connexion", connexion);

        
    if (medium != null) {
            //date to string
            Service service = new Service();
            
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("denomination", medium.getDenomination());
            jsonClient.addProperty("presentation", medium.getPresentation());
            if(medium instanceof Astrologue) {
                    jsonClient.addProperty("type", "Astrologue");
                    Astrologue a = (Astrologue)medium;
                    jsonClient.addProperty("Formation",a.getFormation());
                    jsonClient.addProperty("Promotion",a.getPromotion());
            } else if(medium instanceof Cartomancier) {
                    jsonClient.addProperty("type", "Cartomancier");
            } else {
                    jsonClient.addProperty("type", "Spirite");
                    Spirite s = (Spirite)medium;
                    jsonClient.addProperty("Support",s.getSupport());
            }
            jsonClient.addProperty("id", medium.getIdMedium());
            jsonClient.addProperty("photo", "profileman.png");
            jsonClient.addProperty("genre", "homme");
            if(medium.getGenre() == EnumModele.Genre.FEMME) {
                jsonClient.addProperty("photo", "profilewoman.png");
                jsonClient.addProperty("genre", "femme");
            }
            
            container.add("medium", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
