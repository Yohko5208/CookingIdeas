package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;
import static org.bouncycastle.util.io.Streams.readAll;

public class User {

    String id;
    String username;
    String password;
    String first_name;
    String last_name;
    String email;
    String image;
    List<String> plats_realises;
    List<String> plats_aimes;
    List<Plat> plats_crees;
    Long coin;
    String commentaires;
    //Constructors
    public User(){
    }
    public User(String usrname, String pass){
        this.setUsername(usrname);
        this.setPassword(pass);
    }
    public User(String idN, String usrname, String pass, String fname, String lname, String email, List<String> aime, List<String> realise, Long coin){

        this.setId(idN);
        this.setUsername(usrname);
        this.setPassword(pass);
        this.setFirst_name(fname);
        this.setLast_name(lname);
        this.setEmail(email);
        this.setPlats_aimes(aime);
        this.setPlats_realises(realise);
        this.setCoin(coin);

    }
    public User(String idN, String usrname, String comm){
        this.setId(idN);
        this.setUsername(usrname);
        this.setCommentaires(comm);
    }

    //Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setImage(String img){ this.image=img;}

    public void setPlats_realises(List<String> plats_realises) {
        this.plats_realises = plats_realises;
    }

    public void setPlats_aimes(List<String> plats_aimes) {
        this.plats_aimes = plats_aimes;
    }

    public void setPlats_crees(List<Plat> plats_crees) {
        this.plats_crees = plats_crees;
    }

    public void setCoin(Long coin) {
        this.coin = coin;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    //Getters

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail(){return email;}

    public String getImage(){return image;}

    public List<String> getPlats_realises() {
        return plats_realises;
    }

    public List<String> getPlats_aimes() {
        return plats_aimes;
    }

    public List<Plat> getPlats_crees() {
        return plats_crees;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Long getCoin() {
        return coin;
    }

    //Functions
    public String login(String username, String password) throws Exception{
        JSONParser parser = new JSONParser();

            URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/FindUser?identifiant="+username+"&password="+password); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            JSONObject statusObj=null;
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray a = (JSONArray) parser.parse(inputLine);
                statusObj = (JSONObject)a.get(1);
            }

            in.close();


        //JSONArray json=(JSONArray)o;;
        System.out.println(statusObj);
        //String status = (String) statusObj.get("status");
        String message = (String) statusObj.get("message");
        System.out.println(message);
        return message;
    }
    public User getUser(String username, String password) throws Exception{
        JSONParser parser = new JSONParser();

        URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/FindUser?identifiant="+username+"&password="+password); // URL to Parse
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        JSONObject statusObj=null;
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);
            JSONArray b = (JSONArray)a.get(0);
            statusObj = (JSONObject)b.get(0);
        }

        in.close();


        //JSONArray json=(JSONArray)o;
        //String status = (String) statusObj.get("status");
        String id = (String) statusObj.get("id");
        System.out.println(id);
        String usrname=(String) statusObj.get("identifiant");
        String pass=(String) statusObj.get("password");
        String fname=(String) statusObj.get("name");
        String lname=(String) statusObj.get("surname");
        String email=(String) statusObj.get("email");
        Long coin = (Long) statusObj.get("coin");
        List<String> realise= new ArrayList<>();
        JSONArray j = (JSONArray)statusObj.get("platRealise");
        for (Object o: j) {
            JSONObject obj = (JSONObject)o;
            realise.add((String)obj.get("id"));
        }
        List<String> aime=new ArrayList<>();
        j=(JSONArray) statusObj.get("platAime");
        for (Object o: j) {
            JSONObject obj = (JSONObject)o;
            aime.add((String)obj.get("id"));
        }
        User u = new User(id, usrname, pass, fname, lname, email, aime, realise, coin);

        return u;
    }
    public List<Plat> getPlatUser(int indice) throws Exception{
        List<Plat> ans = new ArrayList<>();
        if(indice==1){
            for (String s: this.getPlats_realises()) {
                Plat p = new Plat();
                p=p.parseJson(p.getPLatById(s));
                ans.add(p);
            }
        }
        if(indice==2){
            for (String s: this.getPlats_aimes()) {
                Plat p = new Plat();
                p=p.parseJson(p.getPLatById(s));
                ans.add(p);
            }
        }
        return ans;
    }
    public Boolean register(User u){
        try{
            JSONParser parser = new JSONParser();
            String usrname=u.getUsername();
            String password=u.getPassword();
            String fname=u.getFirst_name();
            String lname=u.getLast_name();
            String email=u.getEmail();
            URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/InsertUser?identifiant="+usrname+"&name="+fname+"&surname="+lname+"&email="+email+"&password="+password);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            JSONObject statusObj=null;
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                statusObj = (JSONObject) parser.parse(inputLine);
            }

            in.close();


            //JSONArray json=(JSONArray)o;;
            System.out.println(statusObj);
            //String status = (String) statusObj.get("status");
            String message = (String) statusObj.get("status");
            System.out.println(message);
            if(Objects.equals(message, "OK")){
                return true;
            }
            else
                return false;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public User refresh()throws Exception{
        return getUser(this.username, this.password);
    }
}
