package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

class Plat {
    String id;
    String name;
    String image;
    String level;
    int preparation_time;
    int cooking_time;
    List<String> ingredients;
    List<String> recipe;
    List<User> commentaries;
    int nbRealisation;
    int nbLike;

    //Constructors
    public Plat(){

    }
    public Plat(String idN, String nom, String img, String lvl, int prep, int cook, List<String> ingr, List<String> recip, List<User> comm, int nb, int nb1){
        this.setId(idN);
        this.setName(nom);
        this.setImage(img);
        this.setLevel(lvl);
        this.setPreparation_time(prep);
        this.setCooking_time(cook);
        this.setIngredients(ingr);
        this.setRecipe(recip);
        this.setCommentaries(comm);
        this.setNbRealisation(nb);
        this.setNbLike(nb1);
    }

    //Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setCommentaries(List<User> commentaries) {
        this.commentaries = commentaries;
    }

    public void setRecipe(List<String> recipe) {
        this.recipe = recipe;
    }

    public void setNbRealisation(int nbRealisation) {
        this.nbRealisation = nbRealisation;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }
    //Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getLevel() {
        return level;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getRecipe() {
        return recipe;
    }

    public List<User> getCommentaries() {
        return commentaries;
    }

    public int getNbRealisation() {
        return nbRealisation;
    }

    public int getNbLike() {
        return nbLike;
    }

    public JSONObject getPLatById(String id) throws Exception{
        JSONParser parser = new JSONParser();
        Plat ans=new Plat();
        URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/PlatList"); // URL to Parse
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        JSONObject obj=null;
        String inputLine;
        int i=0;
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);
            for(Object o : a){
                JSONObject json=(JSONObject)o;
                if(json.get("id").equals(id)){
                    obj=json;
                    break;
                }
            }

        }
        in.close();
        return obj;

    }
    public Plat parseJson(JSONObject obj) throws Exception{
        String id=(String)obj.get("id");
        String name=(String)obj.get("nom");
        System.out.println(name);
        String image=(String)obj.get("image");
        System.out.println(image);
        String level=(String)obj.get("niveau");
        System.out.println(level);
        int prep=Integer.parseInt((String)obj.get("preparation"));
        System.out.println(prep);
        int cuisson=Integer.parseInt((String)obj.get("cuisson"));
        System.out.println(cuisson);
        List<String> ingr=new ArrayList<String>();
        JSONArray ing = (JSONArray)obj.get("ingredient");
        System.out.println(ing);
        for (Object o: ing)
        {
            System.out.println(o);
            ingr.add((String)o);
        }
        List<String> recipe=new ArrayList<String>();
        JSONArray recip=(JSONArray)obj.get("recette");
        for (Object o: recip)
        {
            recipe.add((String)o);
            System.out.println(o);
        }
        List<User> commentaires=new ArrayList<User>();
        JSONArray comms=(JSONArray)obj.get("commentaire");
        System.out.println(comms);
        if(comms!=null){
            for(Object o: comms){
                JSONObject json=(JSONObject)o;
                System.out.println(json);
                String nId = (String)json.get("id");
                String nUname =  (String)json.get("identifiant");
                String com = (String)json.get("commentaire");
                System.out.println(nId);
                System.out.println(nUname);
                System.out.println(com);
                User usr=new User(nId, nUname, com);
                commentaires.add(usr);
                System.out.println("OK");
            }
        }
        System.out.println("Nivoaka");
        int nbLike=Integer.valueOf(((Long)obj.get("nombrejaime")).intValue());
        System.out.println(nbLike);
        int nbReal=Integer.valueOf(((Long)obj.get("nombrerealisation")).intValue());
        System.out.println(nbReal);

        Plat ans=new Plat(id, name, image, level, prep, cuisson, ingr, recipe, commentaires, nbReal, nbLike);
        //JSONArray json=(JSONArray)o;;
        //String status = (String) statusObj.get("status");

        return ans;
    }
    public List<Plat> getMostLiked() throws Exception{
        List<Plat> ans = new ArrayList<>();
        JSONParser parser = new JSONParser();
        URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/PlatLike"); // URL to Parse
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        int i=0;
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);
            int j=0;
            for(Object o : a){
                JSONObject json=(JSONObject)o;
                Plat temp = this.parseJson(json);
                ans.add(temp);
                if(j==4){
                    break;
                }
                j++;
            }

        }
        in.close();
        return ans;
    }
    public List<Plat> getMostRealised() throws Exception{
        List<Plat> ans = new ArrayList<>();
        JSONParser parser = new JSONParser();
        URL oracle = new URL("https://nameless-escarpment-94857.herokuapp.com/PlatRealisation"); // URL to Parse
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        int i=0;
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);
            int j=0;
            for(Object o : a){
                JSONObject json=(JSONObject)o;
                Plat temp = this.parseJson(json);
                ans.add(temp);
                if(j==4){
                    break;
                }
                j++;
            }

        }
        in.close();
        return ans;
    }
}
