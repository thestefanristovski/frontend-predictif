package fr.insalyon.dasi.dasi.web.controleur.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancier;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.EnumModele;
import fr.insalyon.dasi.metier.modele.EnumModele.Genre;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 */
public class AjouterCommentaireSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long idConsultation = (Long)request.getAttribute("verification");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (idConsultation != null);
        container.addProperty("connexion", connexion);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
