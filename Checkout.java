package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Checkout extends Application {

	@Override
	public void start(Stage primaryStage) {

		// Maroon Border
		Region maroonBorder = new Region();
		maroonBorder.setStyle("-fx-background-color: maroon;");
		// Height of the maroon border
		maroonBorder.setPrefHeight(100); 

		// Label for "Order Summary"
		Label orderSumLabel = new Label("Order Summary");
		// Font of Label
		orderSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); 
		orderSumLabel.setTextFill(Color.BLACK);
		// X-Coord
		orderSumLabel.setTranslateX(40); 
		// Y-Coord
		orderSumLabel.setTranslateY(-3); 

		VBox orderSumBorder = new VBox(orderSumLabel);
		orderSumBorder.setAlignment(Pos.CENTER_LEFT);
		orderSumBorder.setPadding(new Insets(10, 20, 5, 20));
		orderSumBorder.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));

		// Book list
		VBox bookList = new VBox(150);
		for (int i = 1; i <= 3; i++) {
			HBox bookItem = new HBox(100);
			Label bookIcon = new Label("📖");
			bookIcon.setFont(new Font(50));
			VBox bookDetails = new VBox(
					new Label("*Book Name*"),
					new Label("Seller: ")
					);
			Button deleteButton = new Button("Delete");
			deleteButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
			bookItem.getChildren().addAll(bookIcon, bookDetails, deleteButton);
			bookItem.setAlignment(Pos.CENTER_LEFT);
			bookList.getChildren().add(bookItem);
		}
		bookList.setPadding(new Insets(10, 20, 50, 20));
		bookList.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		bookList.setPrefWidth(400);
		bookList.setTranslateX(50);


		// Bill details
		VBox billDetails = new VBox(10);
		billDetails.setPadding(new Insets(20));
		billDetails.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		billDetails.getChildren().addAll(
				new Label("Your Order:"),
				new Label("Book 1: "),
				new Label("Book 2: "),
				new Label("Book 3: "),
				new Separator(),
				new Label("Total (before tax): "),
				new Label("Tax (7%): "),
				new Label("Total (after tax): ")
				);

		/*Label confirmationLabel = new Label("Order confirmed!");
		confirmationLabel.setFont(new Font("Arial", 16));
		confirmationLabel.setTextFill(Color.GREEN);
		billDetails.getChildren().add(confirmationLabel);*/

		// Buttons
		HBox buttonBox = new HBox(20);
		Button cancelButton = new Button("CANCEL");
		cancelButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-weight: bold;");
		Button placeOrderButton = new Button("PLACE ORDER");
		placeOrderButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-weight: bold;");
		buttonBox.getChildren().addAll(cancelButton, placeOrderButton);
		buttonBox.setAlignment(Pos.CENTER);

		VBox billSection = new VBox(20, billDetails, buttonBox);
		billSection.setAlignment(Pos.TOP_CENTER);
		billSection.setPrefWidth(300);

		// Main layout
		HBox mainLayout = new HBox(bookList, billSection);
		mainLayout.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
		
		// Ensure mainLayout expands properly
		mainLayout.setPrefHeight(750); 
		HBox.setHgrow(bookList, Priority.ALWAYS);
		HBox.setHgrow(billSection, Priority.ALWAYS);

		// Combines all sections into root
		VBox root = new VBox(maroonBorder, orderSumBorder, mainLayout);

		// Makes sure root expands to fit the scene
		VBox.setVgrow(mainLayout, Priority.ALWAYS);


		Scene scene = new Scene(root, 1500, 750);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}