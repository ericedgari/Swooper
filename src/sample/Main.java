package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    Stage window;
    static String userNameLoggedIn;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Swooper");

        window.setOnCloseRequest(e ->
        {
            e.consume();
            closeProgram();
        });

        //Declare a new gridpane for the login window
        GridPane gridPane =  new GridPane();
        //Set padding of the gridpane
        gridPane.setPadding(new Insets(10,10,10,10));
        //Set the vertical gap to 8 pixels for each element inside it
        gridPane.setVgap(8);
        //Set the horizontal gap to 10 pixels for each element in side it
        gridPane.setHgap(10);

        //Subtitle  Welcome Label
        Label welcomeLabel = new Label("Welcome, Please Login !");
        GridPane.setConstraints(welcomeLabel,0,0);

        //Declare new USERNAME Label also set the position.
        Label userNameLabel =  new Label("Username : ");
        GridPane.setConstraints(userNameLabel,1,1);

        //Declare new USERNAME TextField to Input name of the admin alse set the position and the prompt text
        TextField userNameInput =  new TextField("");
        userNameInput.setPromptText("Your Username");
        GridPane.setConstraints(userNameInput, 2,1);

        //Declare password label also set the position
        Label passLabel = new Label("Password : ");
        GridPane.setConstraints(passLabel,1,2);

        //Create a final variable passInput that was created from Password Field Class.
        //Aet the prompt text and also the position
        final PasswordField passInput = new PasswordField();
        passInput.setPromptText("Your Password");
        GridPane.setConstraints(passInput,2,2);

        //Create a login Button to log in also set the position.
        Button logInButton =  new Button("Log In");
        GridPane.setConstraints(logInButton, 2,3);

        //Add a method to get all the elements and put it inside the gridpane
        gridPane.getChildren().addAll(userNameLabel, userNameInput, passLabel, passInput, logInButton, welcomeLabel);
        //Set the Alignment position to center
        gridPane.setAlignment(Pos.CENTER);


        //Declare a new logInScene and set the width along with the height
        Scene logInScene =  new Scene(gridPane,600,300);
        //Get the StyleSheet for the Login Scene from a css file named Color.css
        //Set the Window or the Primary Stage to the Login Scene first
        window.setScene(logInScene);
        //Show the window
        //This will display the login Scene
        window.show();

        //Again in this part i use lambda expression
        //The Purpose is that every time there is an action in the loginbutton it will run the block of code inside the lambda expression
        logInButton.setOnAction(event ->
        {
            //This conditional Statement here is to validate whether those textfield (username and pass) are empty.
            //If its true (empty), the program will show an error message to tell the user that they must fill in order to access the inventory
            if (userNameInput.getText().isEmpty() || passInput.getText().isEmpty())
            {
                //Declare alert box to tell the user that all field must be filled
                Alert a =  new Alert(Alert.AlertType.WARNING);
                //Set the title of the alert box
                a.setTitle("Warning !");
                //Set the Message inside
                a.setContentText("All Field must be Filled !");
                // Get the Stage.
                Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
                //Show the alert box
                a.show();
            }
            //Conditional Statement to load the FXML
            else
            {
                String databaseURL = "jdbc:mysql://remotemysql.com:3306/fvLuF0YWF7";
                String connectionUsername = "fvLuF0YWF7";
                String connectionPassword = "FH81vMRjVU";
                Parent root = null;

                try {
                    Connection connection = DriverManager.getConnection(databaseURL, connectionUsername, connectionPassword);

                    String query = "SELECT username, AES_DECRYPT(password, 'secret') AS pswd FROM Users";

                    PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        if (resultSet.getString("username").equals(userNameInput.getText()) && resultSet.getString("pswd").equals(passInput.getText())) {
                            if(userNameInput.getText().contains("Admin")) {
                                try {
                                    //Declare root from Parent Class (Node) and load the fxml as root.
                                    root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if(userNameInput.getText().contains("Cashier")){
                                userNameLoggedIn = userNameInput.getText();
                                try {
                                    //Declare root from Parent Class (Node) and load the fxml as root.
                                    root = FXMLLoader.load(getClass().getResource("cashier.fxml"));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            connection.close();
                            window.setTitle("Swooper");
                            Scene inventoryScene = new Scene(root, 1800, 1200);
                            window.setScene(inventoryScene);
                            window.show();
                            return;
                        }
                    }
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Warning");
                    a.setContentText("Invalid username and/or password!");
                    a.show();
                    connection.close();


                }catch (SQLException e)
                {
                    e.printStackTrace();
                }

                //In this part i assign a function if the window is closed by the user using the x red button that all app has on the top left.
                //I use lambda expression so it means that every event which refers to the x red button is clicked will apply the method inside the lambda
                window.setOnCloseRequest(event1 ->
                {
                    //These function are the same as i mentioned above, the difference is this is for the Fxml
                    event1.consume();
                    closeProgram();
                });
        }
    });
}


    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("WARNING !", "Are you Sure to Close Swooper?");
        if (answer) {
            window.close();
        }
    }
}

