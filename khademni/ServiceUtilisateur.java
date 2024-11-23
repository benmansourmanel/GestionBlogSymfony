/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.WalkthruForm;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import static jdk.nashorn.internal.runtime.Debug.id;
import com.mycomany.entities.Utilisateur;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
     private ArrayList<Utilisateur> utilisateurs;
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    /*
     //***** version correcte Signup  sans session ************
    public void signup(TextField cin,TextField nom,TextField prenom,TextField password,TextField mail, ComboBox<String> role ,TextField tel, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/new?cin="+cin.getText()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
                "&password="+password.getText().toString()+"&mail="+mail.getText().toString()+"&role="+role.getSelectedItem().toString()+"&tel="+tel.getText();
        
        req.setUrl(url);
        
        //Control saisi
        if(cin.getText().equals(" ") &&nom.getText().equals(" ") && prenom.getText().equals(" ")&& password.getText().equals(" ") && mail.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }*/
    
    
    //Signup
public void signup(TextField cin,TextField nom,TextField prenom,TextField password,TextField mail, ComboBox<String> role ,TextField tel, Resources res ) {

    String url = Statics.BASE_URL+"/new?cin="+cin.getText()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
            "&password="+password.getText().toString()+"&mail="+mail.getText().toString()+"&role="+role.getSelectedItem().toString()+"&tel="+tel.getText();
    
    req.setUrl(url);
    
    //Control saisi
    if(cin.getText().equals(" ") &&nom.getText().equals(" ") && prenom.getText().equals(" ")&& password.getText().equals(" ") && mail.getText().equals(" ")) {
        
        Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
        
    }
    
    //hethi wa9t tsir execution ta3 url 
    req.addResponseListener((e)-> {
        byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
        String responseData = new String(data);//ba3dika na5o content 
        
        System.out.println("data ===>"+responseData);
        
        // Parsage de la réponse JSON
        JSONParser j = new JSONParser();
        try {
            Map<String,Object> user = j.parseJSON(new CharArrayReader(responseData.toCharArray()));
            float id = Float.parseFloat(user.get("id").toString());
            
            // Stockage de l'id de l'utilisateur dans la session
            SessionManager.setId((int) id);
            SessionManager.setNom(user.get("NOM").toString());
            SessionManager.setMail(user.get("Mail").toString());
            System.out.println("current user ==>"+SessionManager.getMail()+","+SessionManager.getNom());
            
            //Affichage d'un message de succès
            Dialog.show("Succès","Utilisateur ajouté avec succès","OK",null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });
    
    // Execution de la requête et attente de la réponse
    NetworkManager.getInstance().addToQueueAndWait(req);
}

    
    
   
   //affichage  
    

    public ArrayList<Utilisateur> getAllLUtilisateur(){
       String url = Statics.BASE_URL +"/show/id";
      System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
 
            @Override
            
            public void actionPerformed ( NetworkEvent evt) {
                System.out.println("connexion");
                utilisateurs = parseTasks(new String(req.getResponseData())); //  Logger.getLogger(ServicesProduit.class.getName()).log(Level.SEVERE, null, ex);
                req.removeResponseListener(this);
            }

           private ArrayList<Utilisateur> parseTasks(String string) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateurs;
    }
   
    /*
     public boolean deleteUser(int id) {
    String url = Statics.BASE_URL + "/utilisateur/id" + id;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    request.addResponseListener((e) -> {
        if (request.getResponseCode() == 200) {
            resultOk = true;
        } else {
            // la suppression a échoué
            resultOk = false;
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);
    return resultOk;
}*/
 
  
    
    
    
/* version vrai mais sans session ************ //
  public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/utilisateur/{id}?"+(int)id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
*/
 // delete avec session ************************ //

    public boolean deleteUser() {
    int id = SessionManager.getId(); // récupérer l'id de la personne connectée depuis la session
    String url = Statics.BASE_URL + "/utilisateur/" + id; // utiliser l'id pour construire l'URL de suppression
    
    req.setUrl(url);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            // vérifier si la suppression s'est bien déroulée
            if (req.getResponseCode() == 200) {
                resultOk = true;
            } else {
                resultOk = false;
            }
            
            req.removeResponseListener(this);
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //SignIn
    
    public void signin(TextField mail,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/loginn?mail="+mail.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                 new WalkthruForm(rs).show();
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
              //  SessionManager.setPassword(user.get("Password").toString());
                SessionManager.setNom(user.get("NOM").toString());
                SessionManager.setMail(user.get("Mail").toString());
                
               
                
                //photo 
                
                /*if(user.get("photo") != null)
                    SessionManager.setTel(user.get("tel").toString());*/
                
                System.out.println("currnt user ==>"+SessionManager.getMail()+","+SessionManager.getNom());
                
                
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
 
    
 // sans session ****************************************** /   
   
    public static void EditUser(int id,int cin ,String nom,String prenom,String password,String mail, String role ,int tel) {
        
        String url = Statics.BASE_URL+"/22/edit?id="+id+"&cin="+cin+"&nom="+nom+"&prenom="+prenom+"&password="+password+"&mail="+mail+"&role=" + role +"&tel="+tel;
      
        MultipartRequest req = new MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id",String.valueOf(SessionManager.getId()));
        req.addArgument("cin", String.valueOf(cin));
        req.addArgument("nom",nom);
        req.addArgument("prenom",prenom);
        req.addArgument("password",password);
        req.addArgument("Email",mail);
        req.addArgument("role", role);
        req.addArgument("tel", Integer.toString(tel));
        System.out.println(mail);
        

        req.addResponseListener((response)-> {
            
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
            //if(s.equals("success")){}
            //else {
                //Dialog.show("Erreur","Echec de modification", "Ok", null);
            //}
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
    
    
 //************************* avec session ***********************************//
/*
public static void EditUser(int id, int cin, String nom, String prenom, String password, String mail, String role, int tel) {
    String url = Statics.BASE_URL + "/{id}/edit?" + 21;
    MultipartRequest req = new MultipartRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("cin", String.valueOf(cin));
    req.addArgument("nom", nom);
    req.addArgument("prenom", prenom);
    req.addArgument("password", password);
    req.addArgument("mail", mail);
    req.addArgument("role", role);
    req.addArgument("tel", String.valueOf(tel));

    req.addResponseListener((response) -> {
        byte[] data = (byte[]) response.getMetaData();
        String s = new String(data);
        System.out.println(s);
        // Vérifier la réponse du serveur et mettre à jour la session si nécessaire
        if (s.equals("success")) {
            SessionManager.setNom(nom);
            SessionManager.setPrenom(prenom);
            SessionManager.setMail(mail);
            SessionManager.setRole(role);
        } else {
            Dialog.show("Erreur", "Echec de modification", "Ok", null);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}*/
/*
    public static void EditUser(int cin, String nom, String prenom, String password, String mail, String role, int tel) {
    int id = SessionManager.getId(); // récupérer l'id de la personne connectée depuis la session
    String url = Statics.BASE_URL + "/edit/" + id  ;
    MultipartRequest req = new MultipartRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("cin", String.valueOf(cin));
    req.addArgument("nom", nom);
    req.addArgument("prenom", prenom);
    req.addArgument("password", password);
    req.addArgument("mail", mail);
    req.addArgument("role", role);
    req.addArgument("tel", String.valueOf(tel));

    req.addResponseListener((response) -> {
        byte[] data = (byte[]) response.getMetaData();
        String s = new String(data);
        System.out.println(s);
        // Vérifier la réponse du serveur et mettre à jour la session si nécessaire
        if (s.equals("success")) {
            SessionManager.setNom(nom);
            SessionManager.setPrenom(prenom);
            SessionManager.setMail(mail);
            SessionManager.setRole(role);
        } else {
            Dialog.show("Erreur", "Echec de modification", "Ok", null);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}*/

    
    
    


 
 
 
 
 
 
 
 
 
    
    
    
    
    
    
    
    
      

    
    /*
    public String getPasswordByEmail(String email, Resources rs ) {
      
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }*/
    
  /*
  public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/utilisateur/{id}?id="+(int)id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }*/


