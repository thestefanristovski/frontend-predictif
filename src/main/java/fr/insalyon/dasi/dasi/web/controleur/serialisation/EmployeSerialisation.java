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
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
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
 * @author souid
 */
public class EmployeSerialisation extends Serialisation {
    
        @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        JsonObject container = new JsonObject();
        Employe employe = (Employe) session.getAttribute("employe");
      
        Boolean connexion = (employe != null);
        container.addProperty("connexion", connexion);

        
        
    if (employe != null) {
            //date to string
           // System.out.println("employe != null serialisation");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String date = dateFormat.format(employe.getDateDeNaissance());
            
            Service service = new Service();
            
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            jsonEmploye.addProperty("datedenaissance", date);
            jsonEmploye.addProperty("adresse", employe.getAdressePostale());
            jsonEmploye.addProperty("telephone", employe.getNumeroTelephone());
            jsonEmploye.addProperty("mail", employe.getAdresseElectronique());
            
            List<Consultation> listConsultations = service.listerConsultationParEmploye(employe);
            JsonArray listeCons = new JsonArray();
            for(Consultation c : listConsultations)
            {
                JsonObject j=new JsonObject();
                String dateConsultation = dateFormat.format(c.getDateDemande());
                String medium = c.getMedium().getDenomination();
                String status;
                switch(c.getStatusConsultation()){
                    case ACTIF:
                        status = "Actif";
                        break;
                    case TERMINEE:
                        status = "Terminée";
                        break;
                    case DEMANDEE:
                        status = "Demandée";
                        break;
                    default : 
                        status = "";
                        break;
                }
                j.addProperty("dateConsultation", dateConsultation);
                j.addProperty("mediumConsultation", medium);
                j.addProperty("statusConsultation", status);
                
                listeCons.add(j);
            }
            container.add("employe", jsonEmploye);
            container.add("listeCons", listeCons);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
