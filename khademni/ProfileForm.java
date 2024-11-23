/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Utilisateur;
import com.mycompany.services.ServiceUtilisateur;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

       /* public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(true);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Button modiff = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
        
        String us = SessionManager.getNom();
        System.out.println(us);
        
         TextField id = new TextField(String.valueOf(SessionManager.getId()), "id", 8, TextField.ANY);
        id.setUIID("TextFieldBlack");
        addStringValue("id", id);
        
        TextField cin = new TextField(String.valueOf(SessionManager.getCin()), "cin", 8, TextField.ANY);
        cin.setUIID("TextFieldBlack");
        addStringValue("cin", cin);
        

        TextField nom = new TextField(us);
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
        
        
        TextField prenom = new TextField(SessionManager.getPrenom(), "prenom", 20, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);
        
        
        TextField password = new TextField(SessionManager.getPassword(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("password", password);
        
       
        TextField mail = new TextField(SessionManager.getMail(), "Mail", 20, TextField.EMAILADDR);
        mail.setUIID("TextFieldBlack");
        addStringValue("Mail", mail);
        
    
        TextField tel = new TextField(String.valueOf(SessionManager.getTel()), "tel", 8, TextField.ANY);
        tel.setUIID("TextFieldBlack");
        addStringValue("tel",tel);
        
        
        Supprimer.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("",Supprimer);
        addStringValue("",modiff);
        
        modiff.addActionListener((ActionEvent edit)->{
        InfiniteProgress ip = new InfiniteProgress();
        //final Dialog ipDlg = ip.showInifinieteBlooking();
        
        
        
      //  ServiceUtilisateur.EditUser(SessionManager.getId(),cin.getText(),nom.getText(),prenom.getText(),password.getText(), mail.getText(),tel.getText());
        //ServiceUtilisateur.EditUser(SessionManager.getId(),cin.getText(),nom.getText(),prenom.getText(),SessionManager.getPassword(), mail.getText(),tel.getText());
        //(int id, int cin, String nom, String prenom,String password,String mail, int tel)
        ServiceUtilisateur.EditUser(TOP, TOP, us, us, us, us, TOP);
        SessionManager.setCin(Integer.parseInt(cin.getText()));
        SessionManager.setNom(nom.getText());
        SessionManager.setPrenom(prenom.getText());
        SessionManager.setPassword(password.getText());
        SessionManager.setMail(mail.getText());
        SessionManager.setTel(Integer.parseInt(tel.getText()));


        Dialog.show("Succes","Modifications des coordonnees avec succes","OK",null);
       // ipDlg.dispose();
        refreshTheme();
         
    });}
       
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer Votre Compte ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
               
                if(ServiceUtilisateur.getInstance().deleteUser(SessionManager.getId())) {
                    new SignInForm(res).show();
                }
           
        });
        
        

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
}*/
    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(true);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Button modiff = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
        
        String us = SessionManager.getNom();
        System.out.println(us);
        
         TextField cin = new TextField(String.valueOf(SessionManager.getCin()), "cin", 8, TextField.ANY);
        cin.setUIID("TextFieldBlack");
        addStringValue("cin", cin);

        TextField nom = new TextField(us);
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
        
       
        TextField prenom = new TextField(String.valueOf(SessionManager.getPrenom()), "prenom", 8, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);
        
        TextField mail = new TextField(SessionManager.getMail(), "E-Mail", 20, TextField.EMAILADDR);
        mail.setUIID("TextFieldBlack");
        addStringValue("Mail", mail);
        
        TextField password = new TextField(SessionManager.getPassword(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        TextField role = new TextField(SessionManager.getRole(), "votre Role", 20, TextField.ANY);
        role.setUIID("TextFieldBlack");
        addStringValue("role", role);
        
        
        TextField tel = new TextField(String.valueOf(SessionManager.getTel()), "tel", 8, TextField.ANY);
        tel.setUIID("TextFieldBlack");
        addStringValue("tel", tel);
        
        
        
        Supprimer.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("",Supprimer);
        addStringValue("",modiff);
        
        modiff.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent edit) {
                InfiniteProgress ip = new InfiniteProgress();
                 //final Dialog ipDlg = ip.showInifinieteBlooking();
   ServiceUtilisateur.EditUser(SessionManager.getId(), Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), password.getText(), mail.getText(),role.getText(), Integer.parseInt(tel.getText()));
  //*   ServiceUtilisateur.EditUser(SessionManager.getId(), Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), password.getText(), mail.getText(), role.getText(), Integer.parseInt(tel.getText()));
  //         ServiceUtilisateur.EditUser( Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), password.getText(), mail.getText(), role.getText(), Integer.parseInt(tel.getText()));
                
                SessionManager.setCin(Integer.parseInt(cin.getText()));
                SessionManager.setNom(nom.getText());
                SessionManager.setPrenom(prenom.getText());
                SessionManager.setPassword(password.getText());
                SessionManager.setMail(mail.getText());
                 SessionManager.setRole(role.getText());
                SessionManager.setTel(Integer.parseInt(tel.getText()));
                Dialog.show("Succes","Modifications des coordonnees avec succes","OK",null);
                // ipDlg.dispose();
                refreshTheme();
            }
        });
        
        Supprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer Votre Compte ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
               
               // if(ServiceUtilisateur.getInstance().deleteUser(SessionManager.getId())) {
                if(ServiceUtilisateur.getInstance().deleteUser()) {
                    new SignInForm(res).show();
                }
           
        });
        
        

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
    }

    ProfileForm(Resources res, Utilisateur u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
