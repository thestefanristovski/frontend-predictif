package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ListerMediumsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        List<Medium> listMediums = service.listerMediums();

        request.setAttribute("listMediums", listMediums);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
       /* HttpSession session = request.getSession();
        if (listMediums != null) {
            session.setAttribute("error", "error");
        }
        else {
            session.removeAttribute("error");
        }*/
    }
    
}
