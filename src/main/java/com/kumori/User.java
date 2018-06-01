package com.kumori;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class User {

        private String action;
        private String userid;
        private String firstname;
        private String lastname;
        private String email;

        public String getUserid() {
                return userid;
        }

        public void setUserid(String userid) {
                this.userid = userid;
        }

        public String getFirstname() {
                return firstname;
        }

        public void setFirstname(String firstname) {
                this.firstname = firstname;
        }

        public String getLastname() {
                return lastname;
        }

        public void setLastname(String lastname) {
                this.lastname = lastname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }


        public String getAction() {
                return action;
        }

        public void setAction(String action) {
                this.action = action;
        }

        public void Add(){
                MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
                MongoDatabase db = mongoClient.getDatabase("Playground");
                MongoCollection<Document> users = db.getCollection("Users");

                users.insertOne(new Document("firstname", firstname)
                    .append("lastname", lastname)
                    .append("email", email)
                );
        }

        public List<User> List(){
                MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
                MongoDatabase db = mongoClient.getDatabase("Playground");
                MongoCollection<Document> users = db.getCollection("Users");

                List<User> result = new ArrayList<User>();

                for (Document document : users.find()) {
                    User u = new User();
                    u.firstname = document.get("firstname").toString();
                    u.lastname = document.get("lastname").toString();
                    u.email = document.get("email").toString();
                    u.userid = document.get("_id").toString();
                    result.add(u);
                }

                return result;
        }
}