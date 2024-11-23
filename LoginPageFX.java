package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;

import java.util.Scanner;

public class LoginPageFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Restore books.txt and shoppingcart.txt
        resetBooksFile();
        resetCartFile();

        // Left Panel (Logo and Slogan)
        VBox leftPanel = new VBox();
        leftPanel.setStyle("-fx-background-color: #FF9900;");
        leftPanel.setPadding(new Insets(20));
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setSpacing(20);

        // Logo
        ImageView logoView = new ImageView(new Image(getClass().getResourceAsStream("/images/BookedInLogo.png")));
        logoView.setFitWidth(200);
        logoView.setPreserveRatio(true);
        Circle clip = new Circle(100);
        clip.setCenterX(100);
        clip.setCenterY(100);
        logoView.setClip(clip);

        // Slogan
        Label slogan = new Label("Fuel Your Fire, One Story at a Time!");
        slogan.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        leftPanel.getChildren().addAll(logoView, slogan);

        // Right Panel (Login Form)
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(30));
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setSpacing(20);

        // Login Label
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Radio Buttons for user type selection
        ToggleGroup userTypeGroup = new ToggleGroup();
        RadioButton buyerRadio = new RadioButton("Buyer");
        RadioButton sellerRadio = new RadioButton("Seller");
        RadioButton adminRadio = new RadioButton("Admin");
        buyerRadio.setToggleGroup(userTypeGroup);
        sellerRadio.setToggleGroup(userTypeGroup);
        adminRadio.setToggleGroup(userTypeGroup);
        buyerRadio.setSelected(true);

        HBox radioButtons = new HBox(15, buyerRadio, sellerRadio, adminRadio);
        radioButtons.setAlignment(Pos.CENTER);

        // ASU ID and Password fields
        TextField asuIdField = new TextField();
        asuIdField.setPromptText("ASU ID");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        asuIdField.setMaxWidth(250);
        passwordField.setMaxWidth(250);

        // Login Button
        Button loginButton = new Button("Login →");
        loginButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-padding: 10 20;");
        loginButton.setMaxWidth(250);

        // Message Label
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        // Login Button Action
        loginButton.setOnAction(e -> {
            String asuId = asuIdField.getText().trim();
            String password = passwordField.getText().trim();
            String selectedRole = ((RadioButton) userTypeGroup.getSelectedToggle()).getText();

            if (asuId.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter your ASU ID and password.");
            } else if (validateCredentials(asuId, password, selectedRole)) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Login Successful!");

                // Redirect to the appropriate page based on the selected role
                if (selectedRole.equals("Buyer")) {
                    BuyerPage buyerPage = new BuyerPage();
                    buyerPage.start(primaryStage); // Redirect to Buyer Page
                } else if (selectedRole.equals("Seller")) {
                    SellerPageUI sellerPage = new SellerPageUI();
                    sellerPage.start(primaryStage); // Redirect to Seller Page
                } else if (selectedRole.equals("Admin")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Did not implement Admin functionality.");
                    alert.show(); // Display the message for admin
                }
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Invalid credentials or mismatched role.");
            }
        });

        // Add functionality to trigger login when Enter is pressed
        passwordField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginButton.fire(); // Simulates clicking the Login button
            }
        });

        // Hyperlink for Sign-Up
        Hyperlink signUpLink = new Hyperlink("Don't have an account? Sign Up");
        signUpLink.setStyle("-fx-text-fill: #8B0000; -fx-font-size: 12px;");
        signUpLink.setOnAction(e -> {
            SignUpPage signUpPage = new SignUpPage();
            signUpPage.showSignUpPage(primaryStage);
        });

        // Add elements to the right panel
        rightPanel.getChildren().addAll(loginLabel, radioButtons, asuIdField, passwordField, loginButton, messageLabel, signUpLink);

        // Main layout
        HBox mainLayout = new HBox();
        mainLayout.getChildren().addAll(leftPanel, rightPanel);
        mainLayout.setSpacing(20);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);
        leftPanel.setPrefWidth(350);

        Scene scene = new Scene(mainLayout, 800, 400);
        primaryStage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setTitle("BookedIn - Login");
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }

    // Validate credentials from users.txt file
    private boolean validateCredentials(String asuId, String password, String role) {
        try (Scanner scanner = new Scanner(new File("src/users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userDetails = line.split(",");

                // Validate the number of fields in the line
                if (userDetails.length != 5) {
                    System.out.println("Invalid line format: " + line);
                    continue; // Skip this line and move to the next
                }

                String fileAsuId = userDetails[0].trim();
                String filePassword = userDetails[1].trim();
                boolean isBuyer = Boolean.parseBoolean(userDetails[2].trim());
                boolean isSeller = Boolean.parseBoolean(userDetails[3].trim());
                boolean isAdmin = Boolean.parseBoolean(userDetails[4].trim());

                // Check credentials and role match
                if (fileAsuId.equals(asuId) && filePassword.equals(password)) {
                    if ((role.equals("Buyer") && isBuyer) ||
                            (role.equals("Seller") && isSeller) ||
                            (role.equals("Admin") && isAdmin)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while validating credentials: " + e.getMessage());
        }
        return false; // Return false if no match is found
    }

    // Reset books.txt from books_new.txt
    private void resetBooksFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books_new.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while resetting books.txt: " + e.getMessage());
        }
    }

    // Reset shoppingcart.txt from shoppingcart_new.txt
    private void resetCartFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("shoppingcart_new.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("shoppingcart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while resetting shoppingcart.txt: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
