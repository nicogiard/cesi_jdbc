package com.company.services;

import com.company.models.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        Statement statement = null;
        try {
            statement = DBService.get().getConnection().createStatement();

            String sql = "SELECT * FROM Produit;";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                Integer id = results.getInt("id");
                String nom = results.getString("nom");

                Produit produit = ProduitService.creerProduit(id, nom);
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }
}
