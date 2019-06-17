package com.company;

import com.company.models.Produit;
import com.company.models.Utilisateur;
import com.company.services.ProduitService;
import com.company.services.UtilisateurService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://127.0.0.1/jdbc_cesi?user=root&password=coaxys&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
//
//            Connection connection = DriverManager.getConnection(url);
//
////            // TODO : ecrire le code qui insere dans la table produit le nom "lando"
////            PreparedStatement preparedStatement = connection.prepareStatement(
////                    "INSERT INTO Produit (`nom`) VALUES (?)");
////            preparedStatement.setString(1, "lando");
////            preparedStatement.execute();
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



        List<Produit> produits = ProduitService.findAll();

        List<Utilisateur> utilisateurs = UtilisateurService.findAll();

    }
}
