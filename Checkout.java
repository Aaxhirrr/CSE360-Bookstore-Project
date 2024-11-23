package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
//import java.util.ArrayList;
//import java.util.List;

public class Checkout extends Application {

	/*private final List<Book> cartList = new ArrayList<>();

	public Checkout(List<Book> cartList) {
		this.cartList = cartList;
	}*/

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

		// Label configurations
		Label orderSumLabel = new Label("Order Summary"); // Content of label
		orderSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Font style and size
		orderSumLabel.setTextFill(Color.BLACK); // Set text color
		orderSumLabel.setTranslateX(40); // Horizontal position
		orderSumLabel.setTranslateY(5); // Vertical position

		Label billTextLabel = new Label("Bill"); // Content of label
		billTextLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Font style and size
		billTextLabel.setTextFill(Color.BLACK); // Set text color
		billTextLabel.setTranslateX(40); // Horizontal position
		billTextLabel.setTranslateY(5); // Vertical position

		HBox labelRow = new HBox(600, orderSumLabel, billTextLabel); // Spacing between labels
		labelRow.setAlignment(Pos.CENTER_LEFT); // Align items to the left
		labelRow.setPadding(new Insets(10, 20, 5, 20)); // Padding for spacing

		VBox labelRowBorder = new VBox(labelRow);
		labelRowBorder.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Background color
		/* -------------------------------------------------------------------------------------------------- */

		// Container for book list
		HBox bookListContainer = new HBox();
		bookListContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Background Color
		bookListContainer.setPrefWidth(850); // Width
		bookListContainer.setTranslateX(50); // Horizontal position
		bookListContainer.setTranslateY(25); // Vertical position
		bookListContainer.setMaxHeight(615); // Height position
		bookListContainer.setAlignment(Pos.TOP_CENTER); // Alignment Position
		
		/* IMPLEMENT BOOK FUNCTIONALITY */
		
		
		
		
		/********************************/

		// Container for bill section
		HBox billContainer = new HBox();
		billContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Background color
		billContainer.setPrefWidth(550); // Width
		billContainer.setTranslateX(75); // Horizontal position
		billContainer.setTranslateY(25); // Vertical position
		billContainer.setMaxHeight(460); // Height position
		billContainer.setAlignment(Pos.TOP_CENTER); // Alignment Position

		// IMPLEMENT BILL FUNCTIONALITY (NEEDS WORK)
		/***********************************************************************************/
		Label billDetails = new Label("Your Order:");
		billDetails.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Font style and size
		billDetails.setTranslateX(-180); // Horizontal position
		billContainer.getChildren().add(billDetails);
		
		/*VBox billContent = new VBox();
		billContent.setAlignment(Pos.TOP_RIGHT); // Align content at the top-center of the VBox
		billContent.setPadding(new Insets(10));  // Add padding inside the VBox
		billContent.setTranslateX(-100);
		billContent.setTranslateY(10);

		Label book1 = new Label("Item 1: $10");
		Label book2 = new Label("Item 2: $15");
		Label book3 = new Label("Item 3: $20");
		Label separator = new Label("-----------------------------------------------");
		Label total = new Label("Total: $25");

		billContent.getChildren().addAll(book1, book2, book3, separator, total);
		billContainer.getChildren().add(billContent);*/
		
		billContainer.setPadding(new Insets(20));
	    /***********************************************************************************/
		
		// Create "PLACE ORDER" button
		Button cancelButton = new Button("CANCEL ✕");
		cancelButton.setStyle(
				"-fx-background-color: #4D220C;" + 		   // Dark brown background
						"-fx-text-fill: yellow;" +         // Yellow text color
						"-fx-font-size: 16px;" +           // Font size
						"-fx-padding: 10 20;" +            // Padding around the text
						"-fx-border-radius: 5;" +          // Rounded corners
						"-fx-background-radius: 5;" +      // Matches border radius
						"-fx-cursor: hand;"
				);
		cancelButton.setPrefWidth(225);
		cancelButton.setOnAction(e -> {
			primaryStage.close(); // Close the window when cancel button is clicked
		});

		// Create "PLACE ORDER" button
		Button placeOrderButton = new Button("PLACE ORDER ➔");
		placeOrderButton.setStyle(
				"-fx-background-color: #4D220C;" + 	 	   // Dark brown background
						"-fx-text-fill: yellow;" +         // Yellow text color
						"-fx-font-size: 16px;" +           // Font size
						"-fx-padding: 10 20;" +            // Padding around the text
						"-fx-border-radius: 5;" +          // Rounded corners
						"-fx-background-radius: 5;" +      // Matches border radius
						"-fx-cursor: hand;"
				);
		placeOrderButton.setPrefWidth(225);
		placeOrderButton.setOnAction(e -> {
			Alert confirmationDialog = new Alert(AlertType.INFORMATION); // Pop-up shows up
			confirmationDialog.setTitle("Order Confirmation"); // Title of pop-up
			confirmationDialog.setHeaderText("Order Placed Successfully!"); // Header of pop-up
			confirmationDialog.setContentText("Thank you for your purchase. Your order is being processed."); // Message in pop-up
			confirmationDialog.showAndWait(); // Displays pop-up until user closes it
		});

		// VBox to hold the buttons
		VBox buttonBox = new VBox(20, cancelButton, placeOrderButton);
		buttonBox.setAlignment(Pos.CENTER); // Center the buttons
		buttonBox.setTranslateX(-265); // Horizontal position
		buttonBox.setTranslateY(240); // Vertical position

		// Main layout containing book list, bill, and button sections
		HBox mainLayout = new HBox(bookListContainer, billContainer, buttonBox);
		mainLayout.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Background color
		mainLayout.setPrefHeight(750); // Height

		// Combine all sections into main
		VBox main = new VBox(headerBar, labelRowBorder, mainLayout);

		// Scene configuration and display
		Scene scene = new Scene(main, 1500, 750); // Window size and main layout
		primaryStage.setScene(scene); 
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
