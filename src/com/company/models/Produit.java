package com.company.models;

public class Produit {

    public Integer id;

    public String nom;

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
