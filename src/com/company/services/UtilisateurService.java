package com.company.services;

import com.company.models.Utilisateur;
import com.company.models.exception.SQLServiceException;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {

    public static List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try {
            utilisateurs = DBService.get().select(Utilisateur.class, "SELECT * FROM Utilisateur;");
        } catch (SQLServiceException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

}
