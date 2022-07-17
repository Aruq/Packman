package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;
import java.util.Scanner;

public class Game extends Application {

    private Map map;
    private Player player;
    private Food food;

    @Override
    public void start(Stage stage) throws Exception {

        String s = "/Users/aru/Downloads/demo/src/main/java/com/example/demo/map.txt";
//        String s = "C:/Users/Computer-User/Desktop/map1.txt";
        File file = new File(s);
        if(!file.exists()) {
            System.out.println("File doesnt exists");
        }

        Scanner input = new Scanner(file);
        if(!input.hasNextLine()) {
            System.out.println("The file is empty");
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (input.hasNextLine()) {
            stringBuilder.append(" ");
            stringBuilder.append(input.nextLine());
        }

        map = new Map(stringBuilder.toString());
        player = new MyPlayer(map);
        System.out.println(map.toString());
        System.out.println(player.toString());
        Scene scene = new Scene(map,map.getUnit()*map.getSize(),map.getUnit()*map.getSize());
        food = new Food(map, player);


        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP : player.moveUp(); break;
                case DOWN : player.moveDown(); break;
                case RIGHT: player.moveRight(); break;
                case LEFT: player.moveLeft(); break;
            }
        });

        stage.setScene(scene);
        stage.setTitle("Eater");
        stage.show();

    }
}
class Map extends Pane {
    private static final int UNIT = 50;
    private int size;
    private int[][] map;
    private Position start;
    Map(String s) {

        GridPane pane = new GridPane();
        s = s.substring(1);
        String[] arr = s.split(" ");
        String s1 = arr[0];
        this.size = Integer.parseInt(s1.trim());
        System.out.println(this.size);
        this.map = new int[size][size];

        int count = 1;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                this.map[i][j] = Integer.parseInt(arr[count]);
                count++;


                Rectangle rectangle = new Rectangle(UNIT, UNIT, UNIT, UNIT);
                rectangle.setStroke(Color.BLACK);
                if(map[i][j] == 2) {
                    Position.setUNIT(UNIT);
                    System.out.println(i + " " + j);
                    start = new Position(j ,i );

                }
                if(map[i][j] % 2 == 0) {
                    rectangle.setFill(Color.BLACK);
                }
                else {
                    rectangle.setFill(Color.WHITE);
                }
                pane.add(rectangle,j,i);
                if(count == arr.length)
                    break;
            }
            System.out.println();
        }

        this.getChildren().add(pane);
    }
    public int getUnit() {
        return UNIT;
    }
    public int getSize() {
        return size;
    }
    public int getValue(int x, int y) {

        return map[y][x];
    }
    public Position getStartPosition() {
        return start;
    }
}
class Position {
    private int x;
    private int y;
    private static int UNIT = 0;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static void setUNIT(int unit) {
        UNIT = unit;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public  boolean equals(Position position) {
        return this.getX() == position.getX() && this.getY() == position.getY();
    }
}
 interface Player {
    public void moveRight();
    public void moveLeft();
    public void moveUp();
    public void moveDown();
    public Position getPosition();
}
class MyPlayer implements Player {
    //    private Circle ball;
    private ImageView ball;
    private Map map;
    private Position position;


    MyPlayer(Map map) throws FileNotFoundException {
        this.map = map;
        this.position = map.getStartPosition();
        // ball = new Circle((double)this.position.getX() * map.getUnit() + map.getUnit()/ 2.0, (double)this.position.getY() * map.getUnit() + map.getUnit()/ 2.0, map.getUnit()/ 3.0);
        // ball.setFill(Color.RED);
        InputStream stream = new FileInputStream("/Users/aru/Downloads/demo/src/main/java/com/example/demo/pac.gif");
        Image image = new Image(stream);
        ball = new ImageView(image);
        ball.setX(position.getX() * map.getUnit() + map.getUnit() / 6);
        ball.setY(position.getY() * map.getUnit() + map.getUnit() / 6);
        // ball.setFitHeight(map.getUnit());
        //ball.setFitWidth(map.getUnit());
        ball.setFitHeight(map.getUnit() * 0.77);
        ball.setFitWidth(map.getUnit() * 0.77);

        map.getChildren().add(ball);
    }


     @Override
     public void moveRight() {
         if(this.position.getX()+1> this.map.getSize()) {
             System.out.println("Invalid position!");
         }else if(this.map.getValue((this.position.getX()+1), this.position.getY())%2==1) {
           System.out.println("Invalid position!");
       }
       else {
               ball.setX((int)(ball.getX() + map.getUnit() + 1));
             ball.setRotate(0);
          //  this.ball.setCenterX((int)(this.ball.getCenterX() + map.getUnit() + 1));
           this.setPosition(this.position.getY(), this.position.getX()+1);
        }
     }
     @Override
     public void moveLeft() {
         if(this.position.getX()-1 <0) {
             System.out.println("Invalid position!");
         }
         else if(this.map.getValue(this.position.getX()-1, this.position.getY())%2==1) {
             System.out.println("Invalid position!");
         }
         else {
 //
           //this.ball.setCenterX((int)(this.ball.getCenterX() - map.getUnit() - 1));
             ball.setX((int)(ball.getX() - map.getUnit() - 1));
             ball.setRotate(180);
             this.setPosition(this.position.getY(), this.position.getX()-1);
         }
     }
     @Override
     public void moveUp() {
         if(this.position.getY()-1<0) {
             System.out.println("Invalid position!");
         }
         else if(this.map.getValue(this.position.getX(), this.position.getY()-1)%2==1) {
             System.out.println("Invalid position!");
         }
         else {
           ball.setY((int)(ball.getY() - map.getUnit() - 1));
             ball.setRotate(270);
           //this.ball.setCenterY((int)(this.ball.getCenterY() - map.getUnit() - 1));
             this.setPosition(this.position.getY()-1, this.position.getX());
         }
     }
     @Override
     public void moveDown() {
         if(this.position.getY()+1>this.map.getSize()) {
             System.out.println("Invalid position!");
         }
         else if(this.map.getValue(this.position.getX(), this.position.getY()+1)%2==1) {
             System.out.println("Invalid position!");
         }
         else {
              ball.setY((int)(ball.getY() + map.getUnit() + 1));
           // this.ball.setCenterY((int)(this.ball.getCenterY() + map.getUnit() + 1));
             ball.setRotate(90);
             this.setPosition(this.position.getY()+1, this.position.getX());
         }
     }
     @Override
     public Position getPosition() {
         //return new Position((int)ball.getX()/map.getUnit(), (int)ball.getY()/map.getUnit());
         return position;
     }
     public void setPosition(int y, int x) {
         this.position.setX(x);
         this.position.setY(y);
     }


}