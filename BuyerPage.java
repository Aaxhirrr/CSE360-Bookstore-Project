package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuyerPage extends Application {

    private final List<Book> bookList = new ArrayList<>();
    public final List<Book> cartList = new ArrayList<>(); //LIST OF THINGS IN CART
    private static final String FILE_PATH = "books.txt";
    private static final String IMAGE_FOLDER = "bookimages";

    @Override
    public void start(Stage primaryStage) {
        // check is book images exists
        File imageFolder = new File(IMAGE_FOLDER);
        if (!imageFolder.exists()) {
            imageFolder.mkdir();
        }

        loadBooksFromFile(); // Load books from file on startup

        // adding the stripe maroon on the top of the page
        HBox headerBar = new HBox();
        headerBar.setPrefHeight(50);
        headerBar.setStyle("-fx-background-color: maroon;");

        // adding the logo to top left and making it a circle
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
        
        //shopping cart logo
//        Image cartImage = new Image("file:cart.png", 40, 40, true, true);
//        ImageView cartView = new ImageView(cartImage);
//        cartView.setFitHeight(40);
//        cartView.setFitWidth(40);
//        cartView.setPreserveRatio(false);
//        DropShadow shade = new DropShadow();
//        shade.setRadius(3);
//        shade.setColor(Color.BLACK);
//        cartView.setEffect(shade);
//        
        Button viewCart = new Button("View Cart");
        viewCart.setStyle("-fx-background-color: gold; -fx-text-fill: #8B0000;");
        viewCart.setFont(Font.font("Arial", 14));
        
        
        
        // adding the bookedin name next to the logo
        Label title = new Label("BookedIn");
        title.setStyle("-fx-text-fill: gold; -fx-font-size: 20px; -fx-font-weight: bold;");

        headerBar.getChildren().addAll(logoView, title, viewCart);
        headerBar.setAlignment(Pos.CENTER_LEFT);
        headerBar.setPadding(new Insets(5, 10, 5, 10));
        headerBar.setSpacing(10);

        // main sellers view, where all textboxes and category and such are
        TextField nameField = new TextField();
        nameField.setPromptText("Book Name");
        nameField.setPrefColumnCount(15);

        TextField authorField = new TextField();
        authorField.setPromptText("Author's Name");
        authorField.setPrefColumnCount(15);

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Fiction", "Non-Fiction", "Science", "History");
        categoryComboBox.setPromptText("Category");

        ComboBox<String> conditionComboBox = new ComboBox<>();
        conditionComboBox.getItems().addAll("New", "Used - Good", "Used - Acceptable");
        conditionComboBox.setPromptText("Condition");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        Button uploadButton = new Button("Upload Cover");
        uploadButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: gold;");
        uploadButton.setFont(Font.font("Arial", 14));

        Button addButton = new Button("Add to Library");
        addButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: gold; -fx-font-weight: bold;");
        addButton.setFont(Font.font("Arial", 14));

        HBox inputLayout = new HBox(10);
        inputLayout.getChildren().addAll(nameField, authorField, categoryComboBox, conditionComboBox, priceField, uploadButton, addButton);
        inputLayout.setPadding(new Insets(10));
        inputLayout.setAlignment(Pos.CENTER);

        Separator publishSeparator = new Separator();
        publishSeparator.setStyle("-fx-background-color: black;");
        publishSeparator.setPrefHeight(2);

        // where searching is done (search section)
        ComboBox<String> searchCategory = new ComboBox<>();
        searchCategory.getItems().addAll("Fiction", "Non-Fiction", "Science", "History");
        searchCategory.setPromptText("Search by Category");

        ComboBox<String> searchCondition = new ComboBox<>();
        searchCondition.getItems().addAll("New", "Used - Good", "Used - Acceptable");
        searchCondition.setPromptText("Search by Condition");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: gold;");
        searchButton.setFont(Font.font("Arial", 14));

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: gold;");
        resetButton.setFont(Font.font("Arial", 14));

        HBox searchLayout = new HBox(10);
        searchLayout.getChildren().addAll(searchCategory, searchCondition, searchButton, resetButton);
        searchLayout.setPadding(new Insets(10));
        searchLayout.setAlignment(Pos.CENTER);

        Separator searchSeparator = new Separator();
        searchSeparator.setStyle("-fx-background-color: black;");
        searchSeparator.setPrefHeight(2);

        VBox bookDisplayArea = new VBox(10);
        bookDisplayArea.setPadding(new Insets(10));
        bookDisplayArea.setAlignment(Pos.TOP_CENTER);
        bookDisplayArea.setStyle("-fx-background-color: #f9f9f9;");

        for (Book book : bookList) {
            bookDisplayArea.getChildren().add(createBookEntry(book));
        }

        final File[] selectedImage = {null}; // stores book cover

        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                selectedImage[0] = file;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Image selected: " + file.getName());
                alert.show();
            }
        });

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String author = authorField.getText();
            String category = categoryComboBox.getValue();
            String condition = conditionComboBox.getValue();
            String price = priceField.getText();

            if (name.isEmpty() || author.isEmpty() || category == null || condition == null || price.isEmpty() || selectedImage[0] == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all required information and don't forget to upload a book cover.");
                alert.show();
                return;
            }

            String imageName = selectedImage[0].getName();
            File destination = new File(IMAGE_FOLDER, imageName);

            try {
                Files.copy(selectedImage[0].toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save book cover.");
                alert.show();
                return;
            }

            Book book = new Book(name, author, category, condition, price, imageName);
            bookList.add(book);
            saveBookToFile(book);

            HBox bookEntry = createBookEntry(book);
            bookDisplayArea.getChildren().add(bookEntry);

            nameField.clear();
            authorField.clear();
            categoryComboBox.setValue(null);
            conditionComboBox.setValue(null);
            priceField.clear();
            selectedImage[0] = null;
        });

        // search functionality
        searchButton.setOnAction(event -> {
            String selectedCategory = searchCategory.getValue();
            String selectedCondition = searchCondition.getValue();

            List<Book> filteredBooks = bookList.stream()
                    .filter(book -> (selectedCategory == null || book.getCategory().equals(selectedCategory)) &&
                                    (selectedCondition == null || book.getCondition().equals(selectedCondition)))
                    .collect(Collectors.toList());

            bookDisplayArea.getChildren().clear();

            if (filteredBooks.isEmpty()) {
                Label noResults = new Label("No books match the selected filters.");
                noResults.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
                bookDisplayArea.getChildren().add(noResults);
            } else {
                for (Book book : filteredBooks) {
                    bookDisplayArea.getChildren().add(createBookEntry(book));
                }
            }
        });

        // resets search filters
        resetButton.setOnAction(event -> {
            searchCategory.setValue(null);
            searchCondition.setValue(null);

            bookDisplayArea.getChildren().clear();

            for (Book book : bookList) {
                bookDisplayArea.getChildren().add(createBookEntry(book));
            }
        });
        
        //shopping cart button
        viewCart.setOnAction(event -> {
        	//BUTTON TO CONNECT TO RG'S PAGE
        });

        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(headerBar, inputLayout, publishSeparator, searchLayout, searchSeparator, bookDisplayArea);
        mainLayout.setStyle("-fx-background-color: orange;");

        Scene scene = new Scene(mainLayout, 900, 600);
        primaryStage.setTitle("Book Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveBookToFile(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(book.toFileFormat());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    Book book = new Book(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    bookList.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HBox createBookEntry(Book book) {
        HBox bookEntry = new HBox(10);
        bookEntry.setAlignment(Pos.TOP_LEFT);
        bookEntry.setPadding(new Insets(10));
        bookEntry.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");

        ImageView bookImageView = new ImageView(new Image("file:" + IMAGE_FOLDER + "/" + book.getImageName()));
        bookImageView.setFitHeight(100);
        bookImageView.setFitWidth(80);
        bookImageView.setPreserveRatio(true);

        VBox details = new VBox(5);
        details.setAlignment(Pos.TOP_LEFT);
        details.getChildren().addAll(
                new Text("Name: " + book.getName()),
                new Text("Author: " + book.getAuthor()),
                new Text("Category: " + book.getCategory()),
                new Text("Condition: " + book.getCondition()),
                new Text("Price: $" + book.getPrice())
        );
        
 
        //add to cart button
        Button addToCart = new Button("Add to Cart");
        addToCart.setStyle("-fx-background-color: #8B0000; -fx-text-fill: gold;");
        addToCart.setFont(Font.font("Arial", 14)); 
        details.getChildren().addAll(addToCart);
        
        addToCart.setOnAction(event ->{
        	Book added = new Book(book.getName(), book.getAuthor(), book.getCategory(), book.getCondition(), book.getPrice(), book.getImageName());
        	cartList.add(added); 
        	Alert addedConfirmation = new Alert(AlertType.INFORMATION, "Book has been added to cart!");
        	addedConfirmation.show();
        });
        bookEntry.getChildren().addAll(bookImageView, details);
        return bookEntry;
    }

    private static class Book {
        private final String name;
        private final String author;
        private final String category;
        private final String condition;
        private final String price;
        private final String imageName;

        public Book(String name, String author, String category, String condition, String price, String imageName) {
            this.name = name;
            this.author = author;
            this.category = category;
            this.condition = condition;
            this.price = price;
            this.imageName = imageName;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getCategory() {
            return category;
        }

        public String getCondition() {
            return condition;
        }

        public String getPrice() {
            return price;
        }

        public String getImageName() {
            return imageName;
        }

        public String toFileFormat() {
            return String.join(";", name, author, category, condition, price, imageName);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}