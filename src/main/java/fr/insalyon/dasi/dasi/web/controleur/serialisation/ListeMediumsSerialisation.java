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
 * @author DASI Team
 */
public class ListeMediumsSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> listMediums = (List<Medium>)request.getAttribute("listMediums");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (listMediums != null);
        container.addProperty("connexion", connexion);

        if (listMediums != null) {
            JsonArray mediums = new JsonArray();
            for (Medium listMedium : listMediums) {
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("denomination", listMedium.getDenomination());
                jsonClient.addProperty("presentation", listMedium.getPresentation());
                if(listMedium instanceof Astrologue) {
                    jsonClient.addProperty("type", "Astrologue");
                } else if(listMedium instanceof Cartomancier) {
                    jsonClient.addProperty("type", "Cartomancier");
                } else {
                    jsonClient.addProperty("type", "Spirite");
                }
                jsonClient.addProperty("id", listMedium.getIdMedium());
                jsonClient.addProperty("photo", "profileman.png");
                if(listMedium.getGenre() == Genre.FEMME) {
                    jsonClient.addProperty("photo", "profilewoman.png");
                }
                mediums.add(jsonClient);
            }
            container.add("mediums", mediums);
        }
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(container, out);
        }
    }

}
