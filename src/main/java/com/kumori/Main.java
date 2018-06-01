package com.kumori;

import static spark.Spark.*;

public class Main{
    public static void main(String[] args) {
        port(8081);

        //Basic controller for the sake of time
        post("/user", (req, res) -> new UserAction(req.body()).Parse());
    }
}