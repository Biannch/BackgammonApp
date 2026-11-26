module ar.edu.unlu.poo.backgammonapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ar.edu.unlu.poo.backgammonapp to javafx.fxml;
    exports ar.edu.unlu.poo.backgammonapp;
    exports ar.edu.unlu.poo.backgammonapp.controllers;
    exports ar.edu.unlu.poo.backgammonapp.model;
    exports ar.edu.unlu.poo.backgammonapp.views;
    opens ar.edu.unlu.poo.backgammonapp.controllers to javafx.fxml;
}