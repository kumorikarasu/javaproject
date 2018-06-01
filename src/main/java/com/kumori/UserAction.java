package com.kumori;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import spark.Response;

import java.lang.reflect.Type;
import java.util.List;

public class UserAction {
    private User user;

    public UserAction(String body){
        //Parse out json
        try {
            Gson g = new Gson();
            user = g.fromJson(body, User.class);

        }catch(Exception ex){
            //Improper formatted input;
            System.out.println(ex.getMessage());
        }


    }

    public String Parse(){
        //Check what the action is
        if (user.getAction().equals("ADD")){
            user.Add();
        }else if (user.getAction().equals("LIST")){
            return List(user.List());
        }

        return Response();
    }

    public String List(List<User> users){
        Gson g = new GsonBuilder().create();
        Type listOfTestObject = new TypeToken<List<User>>(){}.getType();
        String s = g.toJson(users, listOfTestObject);

        return s;
    }


    public String Response() {
        Gson g = new GsonBuilder().create();

        user.setAction(null);
        String result = g.toJson(user);

        return result;
    }
}
