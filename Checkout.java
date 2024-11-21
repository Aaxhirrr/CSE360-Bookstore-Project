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

        // Maroon border at the top
        Region maroonBorder = new Region();
        maroonBorder.setStyle("-fx-background-color: maroon;"); // Color of maroon border
        maroonBorder.setPrefHeight(100); // Height for maroon border

        // "Order Summary" label config
        Label orderSumLabel = new Label("Order Summary"); // Content of label
        orderSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Set font style and size
        orderSumLabel.setTextFill(Color.BLACK); // Set text color
        orderSumLabel.setTranslateX(40); // Horizontal position
        orderSumLabel.setTranslateY(-3); // Vertical position

        // VBox for the "Order Summary"
        VBox orderSumBorder = new VBox(orderSumLabel);
        orderSumBorder.setAlignment(Pos.CENTER_LEFT); // Centering Position
        orderSumBorder.setPadding(new Insets(10, 20, 5, 20)); // Padding 
        orderSumBorder.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Color

        // List of books
        VBox bookList = new VBox(150); // Spacing between books
        for (int i = 1; i <= 3; i++) { // Creates 3 books
            HBox bookItem = new HBox(100); // Spacing between components of a book
            Label bookIcon = new Label("📖"); // Icon for book
            bookIcon.setFont(new Font(50)); // Icon size
            VBox bookDetails = new VBox(
                new Label("*Book Name*"), // Book Name
                new Label("Seller: ") // Seller
            );
            Button deleteButton = new Button("Delete"); // Delete button for removing a book
            deleteButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;"); // Styling for button
            bookItem.getChildren().addAll(bookIcon, bookDetails, deleteButton); // Add components to bookItem (HBox)
            bookItem.setAlignment(Pos.CENTER_LEFT); // Centering Position
            bookList.getChildren().add(bookItem); // Add each book item to the book list
        }
        bookList.setPadding(new Insets(10, 20, 50, 20)); // Padding for the book list
        bookList.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Color
        bookList.setPrefWidth(400); // Width Position
        bookList.setTranslateX(50); // Horizontal position

        // Section for bill details
        VBox billDetails = new VBox(10); // Vertical spacing
        billDetails.setPadding(new Insets(20)); // Padding for bill details section
        billDetails.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))); // Background color
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

        // Buttons for canceling or placing the order
        HBox buttonBox = new HBox(20); // Horizontal spacing between buttons
        Button cancelButton = new Button("CANCEL"); // Cancel button
        cancelButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-weight: bold;");
        Button placeOrderButton = new Button("PLACE ORDER"); // Place order button
        placeOrderButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-weight: bold;");
        buttonBox.getChildren().addAll(cancelButton, placeOrderButton); // Add buttons to the buttonBox (HBox)
        buttonBox.setAlignment(Pos.CENTER); // Centering Position

        // Combines bill details and buttons into a VBox
        VBox billSection = new VBox(20, billDetails, buttonBox);
        billSection.setAlignment(Pos.TOP_CENTER); // Centering Position
        billSection.setPrefWidth(300); // Width

        // Main layout containing book list and bill section
        HBox mainLayout = new HBox(bookList, billSection);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null))); // Background color
        mainLayout.setPrefHeight(750); // Height
        HBox.setHgrow(bookList, Priority.ALWAYS); // book list grows horizontally
        HBox.setHgrow(billSection, Priority.ALWAYS); // bill section grows horizontally

        // Combine all sections into main
        VBox main = new VBox(maroonBorder, orderSumBorder, mainLayout);
        //VBox.setVgrow(mainLayout, Priority.ALWAYS); // mainLayout grows vertically

        // Scene configuration and display
        Scene scene = new Scene(main, 1500, 750); // Window size and root layout
        primaryStage.setScene(scene); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
