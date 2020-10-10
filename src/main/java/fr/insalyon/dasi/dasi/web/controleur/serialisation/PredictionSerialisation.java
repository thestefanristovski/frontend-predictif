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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 
 */
public class PredictionSerialisation extends Serialisation {
        
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        JsonObject container = new JsonObject();
        ArrayList<String> predictions = (ArrayList<String>) request.getAttribute("predictions");
        JsonObject result = new JsonObject();
        
            
            result.addProperty("amour", predictions.get(0));
            result.addProperty("sante", predictions.get(1));
            result.addProperty("travail", predictions.get(2));
            //System.out.println(predictions.get(2));
           // result.add(amour);
           // result.add(sante);
            //result.add(travail);
        
        container.add("predictions", result);
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
            out.close();
        }
    }
}
