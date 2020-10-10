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
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * 
 */
public class AgenceSerialisation extends Serialisation {
    
        @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        JsonObject container = new JsonObject();
        List<Medium> top5mediums = (List<Medium>) request.getAttribute("top5mediums");
        Boolean connexion = (top5mediums!=null);
        if(top5mediums!=null){
        JsonArray listeMediums = new JsonArray();
        for(int i = 0; i<top5mediums.size(); i++)
        {
            JsonObject medium = new JsonObject();
            medium.addProperty("denomination", top5mediums.get(i).getDenomination());
            medium.addProperty("nbConsultations", top5mediums.get(i).getNbConsultations());
            listeMediums.add(medium);
        }
        container.add("mediums", listeMediums);
        }
        container.addProperty("connexion", connexion);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
