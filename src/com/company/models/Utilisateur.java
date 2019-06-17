package com.company.models;

public class Utilisateur {

    public Integer id;

    public String nom;

    public String prenom;

    public String email;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
