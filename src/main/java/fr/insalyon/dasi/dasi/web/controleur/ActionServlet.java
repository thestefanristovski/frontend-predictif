/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dasi.web.controleur.action.Action;
import fr.insalyon.dasi.dasi.web.controleur.action.AgenceAction;
import fr.insalyon.dasi.dasi.web.controleur.action.AjouterCommentaireAction;
import fr.insalyon.dasi.dasi.web.controleur.action.AuthentifierUtilisateurAction;
import fr.insalyon.dasi.dasi.web.controleur.action.CommencerConsultationAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ConsultationClientAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ConsultationEnCoursAction;
import fr.insalyon.dasi.dasi.web.controleur.action.DeconnecterUtilisateurAction;
import fr.insalyon.dasi.dasi.web.controleur.action.DemanderConsultationAction;
import fr.insalyon.dasi.dasi.web.controleur.action.InscriptionAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ListerMediumsAction;
import fr.insalyon.dasi.dasi.web.controleur.action.MesConsultationsAction;
import fr.insalyon.dasi.dasi.web.controleur.action.PredictionAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ProfilClientAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ProfilEmployeAction;
import fr.insalyon.dasi.dasi.web.controleur.action.ProfilMediumAction;
import fr.insalyon.dasi.dasi.web.controleur.action.TerminerConsultationAction;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.AgenceSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.AjouterCommentaireSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.ClientSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.ConsultationEnCoursSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.DemandeConsultationSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.EmployeSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.InscriptionSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.ListeMediumsSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.MediumSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.MesConsultationsSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.PredictionSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.Serialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.TerminerConsultationSerialisation;
import fr.insalyon.dasi.dasi.web.controleur.serialisation.UtilisateurSerialisation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zineb
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    
        @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");
        Serialisation serialisation = null;

        Action action = null;

        if (todo != null) {
            switch (todo) {
                case "connecter":
                    action = new AuthentifierUtilisateurAction();
                    serialisation = new UtilisateurSerialisation();
                    break;
                case "listmediums":
                    action = new ListerMediumsAction();
                    serialisation = new ListeMediumsSerialisation();
                    break;
                case "profilClient":
                    action = new ProfilClientAction();
                    serialisation = new ClientSerialisation();
                    break;
                case "profilEmploye":
                    action = new ProfilEmployeAction();
                    serialisation = new EmployeSerialisation();
                    break;
                case "profilMedium":
                    action = new ProfilMediumAction();
                    serialisation = new MediumSerialisation();
                    break;
                case "deconnexion":
                    action = new DeconnecterUtilisateurAction();
                    break;
                case "inscrire":
                    action = new InscriptionAction();
                    serialisation = new InscriptionSerialisation();
                    break;
                case "demander":
                    action = new DemanderConsultationAction();
                    serialisation = new DemandeConsultationSerialisation();
                    break;
                case "consultationEnCours":
                    action = new ConsultationEnCoursAction();
                    serialisation = new ConsultationEnCoursSerialisation();
                    break;
                case "generer-prediction":
                    action = new PredictionAction();
                    serialisation = new PredictionSerialisation();
                    break;
                case "terminerConsultation":
                    action = new TerminerConsultationAction();
                    serialisation = new TerminerConsultationSerialisation();
                    break;
                case "ajouterCommentaire":
                    action = new AjouterCommentaireAction();
                    serialisation = new AjouterCommentaireSerialisation();
                    break;
                case "infoClientTermine":
                    action = new ConsultationClientAction();
                    serialisation = new ClientSerialisation();
                    break;
                case "mesConsultations":
                    action = new MesConsultationsAction();
                    serialisation = new MesConsultationsSerialisation();
                    break;
                case "agence":
                    action = new AgenceAction();
                    serialisation = new AgenceSerialisation();
                    break;
                case "commencer":
                    action = new CommencerConsultationAction();
                    serialisation = new AjouterCommentaireSerialisation();
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
