package com.brush_app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Author: Bao Nguyen
 * Fall 2024
 * Short Assignment 10: Paint App
 */
public class App extends Application {

    private static Scene scene;
    private boolean isEraserMode = false;

    public static void main(String[] args) {
        launch();
    }

    public void drawDot(int x, int y, int size, Color color, String shape, Group group) {
        // Shape dotShape;
        if (shape.equals("circle")) {
            Circle dotShape = new Circle();
            dotShape.setCenterX(x);
            dotShape.setCenterY(y);
            dotShape.setRadius(size);
            dotShape.setFill(color);
            group.getChildren().add(dotShape);

        } else if (shape.equals("rectangle")) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX(x);
            rectangle.setY(y);
            rectangle.setWidth(size*2);
            rectangle.setHeight(size*2);
            rectangle.setFill(color);
            group.getChildren().add(rectangle);
        } else {
            Polygon triangle = new Polygon();
            // Define the three vertices of the triangle
            triangle.getPoints().addAll(
                (double) x, (double) y, // Top vertex
                (double) (x - size), (double) (y + size*2), // Bottom-left vertex
                (double) (x + size), (double) (y + size*2) // Bottom-right vertex
            );
            triangle.setFill(color);
            group.getChildren().add(triangle);

        }
        
        
        
    }


    private void preventMousePropagation(Button button) {
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, MouseEvent::consume);
        button.addEventFilter(MouseEvent.MOUSE_DRAGGED, MouseEvent::consume);
    }
    

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Bao's Paint App");
        Brush myBrush = new Brush();


        // set up the canvas
        Group root = new Group();
        // drawDot(10, 10, root);

        scene = new Scene(root, 640, 480);



        // Size buttons
        Button sizeUpButton = new Button("+");
        sizeUpButton.setLayoutX(50);
        sizeUpButton.setLayoutY(0);
        root.getChildren().add(sizeUpButton);
        sizeUpButton.setOnAction((e) -> {
            myBrush.setSize(1);
        });
      

        Button sizeDownButton = new Button("-");
        sizeDownButton.setLayoutX(80);
        sizeDownButton.setLayoutY(0);
        root.getChildren().add(sizeDownButton);
        sizeDownButton.setOnAction((e) -> {
            myBrush.setSize(-1);
        });

        // Color buttons
        Button orangeButton = new Button();
        orangeButton.setStyle("-fx-background-color: orange");
        orangeButton.setPrefSize(25, 20);
        orangeButton.setLayoutX(120);
        orangeButton.setLayoutY(0);
        root.getChildren().add(orangeButton);
        orangeButton.setOnAction((e) -> {
            myBrush.setColor(Color.ORANGE);
        });

        Button blueButton = new Button();
        blueButton.setStyle("-fx-background-color: blue");
        blueButton.setPrefSize(25, 20);
        blueButton.setLayoutX(150);
        blueButton.setLayoutY(0);
        root.getChildren().add(blueButton);
        blueButton.setOnAction((e) -> {
            myBrush.setColor(Color.BLUE);
        });

        Button redButton = new Button();
        redButton.setStyle("-fx-background-color: red");
        redButton.setPrefSize(25, 20);
        redButton.setLayoutX(180);
        redButton.setLayoutY(0);
        root.getChildren().add(redButton);
        redButton.setOnAction((e) -> {
            myBrush.setColor(Color.RED);
        });

        // Shape button
        Button circleButton = new Button();
        circleButton.setShape(new Circle(24));
        circleButton.setMinSize(24, 24); // Adjust size to match the shape
        circleButton.setMaxSize(24, 24);
        circleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
        circleButton.setLayoutX(220);
        circleButton.setLayoutY(0);
        root.getChildren().add(circleButton);
        circleButton.setOnAction((e) -> {
            myBrush.setShape("circle");
        });

        Button rectangleButton = new Button();
        rectangleButton.setShape(new Rectangle(22, 22));
        rectangleButton.setMinSize(24, 22); // Adjust size to match the shape
        // rectangleButton.setMaxSize(27, 27);
        rectangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
        rectangleButton.setLayoutX(250);
        rectangleButton.setLayoutY(0);
        root.getChildren().add(rectangleButton);
        rectangleButton.setOnAction((e) -> {
            myBrush.setShape("rectangle");
        });

        Button triangleButton = new Button();
        triangleButton.setShape(new Polygon(
            0.0, 0.0, // Top point
            60.0, 60.0, // Bottom-right
            -60.0, 60.0 // Bottom-left
        ));
        triangleButton.setMinSize(27, 23); // Adjust size to match the shape
        // triangleButton.setMaxSize(27, 27);
        triangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
        triangleButton.setLayoutX(280);
        triangleButton.setLayoutY(0);
        root.getChildren().add(triangleButton);
        triangleButton.setOnAction((e) -> {
            myBrush.setShape("triangle");
        });        

        // Eraser button
         // Toggle button for Eraser Mode
        Button eraserButton = new Button("Eraser");
        eraserButton.setLayoutX(320);
        eraserButton.setLayoutY(1);
 
        eraserButton.setOnAction(e -> {
            isEraserMode = !isEraserMode; // Toggle eraser mode
            eraserButton.setText(isEraserMode ? "Draw" : "Eraser");
        });
        root.getChildren().add(eraserButton);
     

        // Clear button
        Button clearButton = new Button("Clear");
        root.getChildren().add(clearButton);

        clearButton.setOnAction((actionEvent) -> {
            root.getChildren().clear();
            root.getChildren().add(clearButton);
            root.getChildren().add(sizeDownButton);
            root.getChildren().add(sizeUpButton);
            root.getChildren().addAll(blueButton, orangeButton, redButton, 
            eraserButton, circleButton, rectangleButton, triangleButton);
        });
        
                // handle mouse click
                scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        int x = (int) mouseEvent.getX();
                        int y = (int) mouseEvent.getY();
                        System.out.println(x + " " + y);
                        Color color = myBrush.getColor();
                        if (isEraserMode) color = Color.WHITE;
                        if (y >= 25+myBrush.getSize()) drawDot(x, y, myBrush.getSize(), color, myBrush.getShape(), root);
                        rectangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                        circleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                        triangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                      }
                });
        
                scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        int x = (int) mouseEvent.getX();
                        int y = (int) mouseEvent.getY();
                        System.out.println(x + " " + y);
                        Color color = myBrush.getColor();
                        if (isEraserMode) color = Color.WHITE;
                        if (y >= 25+myBrush.getSize()) drawDot(x, y, myBrush.getSize(), color, myBrush.getShape(), root);
                        rectangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                        circleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                        triangleButton.setStyle("-fx-background-color: " + myBrush.getColorString());
                      }
                });

        stage.setScene(scene);
        stage.show();
    }
   
}