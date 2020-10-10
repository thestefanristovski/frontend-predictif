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
 * 
 */
public class MesConsultationsSerialisation extends Serialisation {
    
            @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        JsonObject container = new JsonObject();
        
        boolean works = Boolean.TRUE == request.getAttribute("work");
        Boolean connexion = false;
        
        if(works){
            connexion = true;
        Long idConsultation = (long) request.getAttribute("idConsultation");
        container.addProperty("connexion", connexion);
        container.addProperty("idConsultation", idConsultation);

        Client client = (Client) request.getAttribute("client");
        Medium medium = (Medium) request.getAttribute("medium");
      
        
        
    if (client != null) {
            //date to string
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String date = dateFormat.format(client.getDateDeNaissance());
            
            Service service = new Service();
            
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("signezodiaque", client.getProfilAstral().getSigneZodiac());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getAdresseElectronique());
            jsonClient.addProperty("datedenaissance", date);
            jsonClient.addProperty("adresse", client.getAdressePostale());
            jsonClient.addProperty("telephone", client.getNumeroTelephone());
            jsonClient.addProperty("signechinois", client.getProfilAstral().getSigneChinois());
            jsonClient.addProperty("couleurportebonheur", client.getProfilAstral().getCouleur());
            jsonClient.addProperty("animaltotem", client.getProfilAstral().getAnimalTotem());
            
            List<Consultation> listConsultations = service.listerConsultationParClient(client);
            JsonArray listeCons = new JsonArray();
            for(Consultation c : listConsultations)
            {
                JsonObject j=new JsonObject();
                 String dateConsultation = dateFormat.format(c.getDateDemande());
                String mediumC = c.getMedium().getDenomination();
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
                j.addProperty("commentaireEmploye", c.getCommentaireEmploye());
                j.addProperty("mediumConsultation", mediumC);
                j.addProperty("statusConsultation", status);
                
                listeCons.add(j);
                
               /* System.out.println(dateConsultation);
                System.out.println(mediumC);
                System.out.println(status);*/
            }
            container.add("client", jsonClient);
            container.add("listeCons", listeCons);
        }
    
    
      

      /*  System.out.println("");
        System.out.println("le medium : ");
        System.out.println(medium);*/
        
        
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
    
        }else
        {
            container.addProperty("connexion", connexion);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
