/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
     private int cin;
    private String nom;
     private String prenom;
    private String mail;
    private String password;
    private String role;
    private int tel;

    public Utilisateur(int id, int cin, String nom, String prenom, String mail, String password, String role, int tel) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.tel = tel;
    }

    public Utilisateur(int cin, String nom, String prenom, String mail, String password, String role, int tel) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.tel = tel;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", password=" + password + ", role=" + role + ", tel=" + tel + '}';
    }

}