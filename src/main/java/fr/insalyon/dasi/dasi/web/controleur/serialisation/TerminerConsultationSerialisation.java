/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 
 */
public class TerminerConsultationSerialisation extends Serialisation {
        
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        JsonObject container = new JsonObject();
        container.addProperty("validation", (Boolean) request.getAttribute("validation"));
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
            out.close();
        }
    }
}
