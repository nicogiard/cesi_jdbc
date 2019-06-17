package com.company;

import com.company.models.Produit;
import com.company.models.Utilisateur;
import com.company.services.ProduitService;
import com.company.services.UtilisateurService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Produit> produits = ProduitService.findAll();
        for (Produit produit : produits) {
            System.out.println(produit);
        }
        List<Utilisateur> utilisateurs = UtilisateurService.findAll();
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
    }
}
