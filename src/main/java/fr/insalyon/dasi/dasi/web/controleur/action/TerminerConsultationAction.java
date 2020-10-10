/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dasi.web.controleur.action;

import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.util.AstroTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 */
public class TerminerConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"),10);
        Service service = new Service();
        Long idFini = service.terminerConsultation(id);
        Boolean bienFini = (idFini != null);
        request.setAttribute("validation", bienFini);
}
}