package com.example.demo;

import javafx.application.Application; import javafx.scene.Scene;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.Pane;
 import javafx.geometry.Insets;
 import javafx.stage.Stage;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
        public class ShowImage extends Application {
 @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws FileNotFoundException {
 // Create a pane to hold the image views
         Pane pane = new HBox(10);
pane.setPadding(new Insets(5, 5, 5, 5));
 //Image image = new Image("/com.example.demo/pac.gif");
 //pane.getChildren().add(new ImageView(image));
     InputStream stream = new FileInputStream("/Users/aru/Downloads/demo/src/main/java/com/example/demo/pac.gif");
     Image image = new Image(stream);
      ImageView imageView2 = new ImageView(image);
 imageView2.setFitHeight(100);
 imageView2.setFitWidth(100);
 pane.getChildren().add(imageView2);

         ImageView imageView3 = new ImageView(image);
 imageView3.setRotate(90);
     pane.getChildren().add(imageView3);
Scene scene = new Scene(pane);


 primaryStage.setTitle("Show"); // Set the stage title
     primaryStage.setScene(scene); // Place the scene in the stage
     primaryStage.show(); // Display the stage
 } }