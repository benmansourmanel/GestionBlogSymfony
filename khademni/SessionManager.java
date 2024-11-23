/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static int cin ; 
    private static String nom ;
    private static String prenom ;
    private static String password ;
    private static String mail; 
    private static String role; 
    private static int tel;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }
    
       
     public static int getCin() {
        return pref.get("cin",cin);
    }

    public static void setCin(int cin) {
         pref.set("cin",cin);
    }
    
    
    
    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }
    
     public static String getPrenom() {
        return pref.get("prenom",prenom);
    }

    public static void setPrenom(String prenom) {
         pref.set("prenom",prenom);
    }

    public static String getMail() {
        return pref.get("mail",mail);
    }

    public static void setMail(String mail) {
         pref.set("mail",mail);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    
    
    public static void setPassword(String password) {
    pref.get("password",password);
}
    public static int getTel() {
        return pref.get("tel",tel);
    }

    public static void setTel(int tel) {
         pref.set("tel",tel);
    }
    
     public static String getRole() {
        return pref.get("role",role);
    }

    public static void setRole(String role) {
         pref.set("role",role);
    }

    
    
    
    
}
