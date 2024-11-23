package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Checkout extends Application {

	@Override
	public void start(Stage primaryStage) {

		// Maroon Stripe
		HBox headerBar = new HBox();
		headerBar.setPrefHeight(50);
		headerBar.setStyle("-fx-background-color: maroon;");

		// Adding logo to top left and making it a circle
		Image logoImage = new Image("file:logobook.png", 40, 40, true, true);
		ImageView logoView = new ImageView(logoImage);
		logoView.setFitHeight(40);
		logoView.setFitWidth(40);
		logoView.setPreserveRatio(false);
		Circle clip = new Circle(20);
		clip.setCenterX(20);
		clip.setCenterY(20);
		logoView.setClip(clip);
		DropShadow shadow = new DropShadow();
		shadow.setRadius(3);
		shadow.setColor(Color.BLACK);
		logoView.setEffect(shadow);
		// adding the bookedin name next to the logo
		Label title = new Label("BookedIn");
		title.setStyle("-fx-text-fill: gold; -fx-font-size: 20px; -fx-font-weight: bold;");

		headerBar.getChildren().addAll(logoView, title);
		headerBar.setAlignment(Pos.CENTER_LEFT);
		headerBar.setPadding(new Insets(5, 10, 5, 10));
		headerBar.setSpacing(10);

		// "Order Summary" label config
		Label orderSumLabel = new Label("Order Summary"); // Content of label
		orderSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Set font style and size
		orderSumLabel.setTextFill(Color.BLACK); // Set text color
		orderSumLabel.setTranslateX(40); // Horizontal position
		orderSumLabel.setTranslateY(10); // Vertical position

		// VBox for the "Order Summary"
		VBox orderSumBorder = new VBox(orderSumLabel);
		orderSumBorder.setAlignment(Pos.CENTER_LEFT); // Centering Position
		orderSumBorder.setPadding(new Insets(10, 20, 5, 20)); // Padding 
		orderSumBorder.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Color
		/* -------------------------------------------------------------------------------------------------- */

		HBox bookListContainer = new HBox();
		bookListContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Color
		bookListContainer.setPrefWidth(850); // Width Position
		bookListContainer.setTranslateX(50); // Horizontal position
		bookListContainer.setTranslateY(25); // Horizontal position
		bookListContainer.setMaxHeight(600); // Limit the maximum height
		bookListContainer.setAlignment(Pos.TOP_CENTER); // Align content to the top

		HBox billContainer = new HBox(150); // Spacing between books
		billContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Color
		billContainer.setPrefWidth(550); // Width Position
		billContainer.setTranslateX(75); // Horizontal position
		billContainer.setTranslateY(25); // Horizontal position
		billContainer.setMaxHeight(475); // Limit the maximum height
		billContainer.setAlignment(Pos.TOP_CENTER); // Align content to the top

		Button cancelButton = new Button("CANCEL ✕");
        cancelButton.setStyle(
            "-fx-background-color: #4D220C;" + // Dark brown background
            "-fx-text-fill: yellow;" +         // Yellow text color
            "-fx-font-size: 16px;" +           // Font size
            "-fx-padding: 10 20;" +            // Padding around the text
            "-fx-border-radius: 5;" +          // Rounded corners
            "-fx-background-radius: 5;" +      // Matches border radius
            "-fx-cursor: hand;"
        );
        cancelButton.setPrefWidth(400);
        
        Button placeOrderButton = new Button("PLACE ORDER ➔");
        placeOrderButton.setStyle(
            "-fx-background-color: #4D220C;" + // Dark brown background
            "-fx-text-fill: yellow;" +         // Yellow text color
            "-fx-font-size: 16px;" +           // Font size
            "-fx-padding: 10 20;" +            // Padding around the text
            "-fx-border-radius: 5;" +          // Rounded corners
            "-fx-background-radius: 5;" +      // Matches border radius
            "-fx-cursor: hand;"
        );
        placeOrderButton.setPrefWidth(400);
        
        HBox cancelBox = new HBox(cancelButton);
        cancelBox.setAlignment(Pos.CENTER); // Center the button
        cancelBox.setTranslateX(-225);
        cancelBox.setTranslateY(210);
        
        HBox placeBox = new HBox(placeOrderButton);
        placeBox.setAlignment(Pos.CENTER); // Center the button
        placeBox.setTranslateX(-450);
        placeBox.setTranslateY(275);
        
		// Main layout containing book list and bill section
		HBox mainLayout = new HBox(bookListContainer, billContainer, cancelBox, placeBox);
		mainLayout.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Background color
		mainLayout.setPrefHeight(750); // Height

		// Combine all sections into main
		VBox main = new VBox(headerBar, orderSumBorder, mainLayout);

		// Scene configuration and display
		Scene scene = new Scene(main, 1500, 750); // Window size and root layout
		primaryStage.setScene(scene); 
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
