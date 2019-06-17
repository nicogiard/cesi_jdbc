package com.company.services;

import com.company.models.Produit;
import com.company.models.exception.SQLServiceException;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

    public static Produit creerProduit(Integer id, String nom) {
        Produit produit = new Produit();
        produit.id = id;
        produit.nom = nom;
        return produit;
    }

    public static List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();

        try {
            produits = DBService.get().select(Produit.class, "SELECT * FROM Produit;");
        } catch (SQLServiceException e) {
            e.printStackTrace();
        }

        return produits;
    }
}
