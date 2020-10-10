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
public class PredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        AstroTest test = new AstroTest();
        String couleur = (String)request.getAttribute("couleur");
        String animal = (String)request.getAttribute("animal");
        int amour = Integer.parseInt(request.getParameter("num1"),10);
        int sante = Integer.parseInt(request.getParameter("num2"),10);
        int travail =  Integer.parseInt(request.getParameter("num3"),10);
        ArrayList<String> predictions = new ArrayList<String>();
        try {
            predictions = (ArrayList<String>) test.getPredictions(couleur, animal, amour, sante, travail);
        } catch (IOException ex) {
            Logger.getLogger(PredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!predictions.isEmpty())
            request.setAttribute("predictions", predictions);
        
    }

}
