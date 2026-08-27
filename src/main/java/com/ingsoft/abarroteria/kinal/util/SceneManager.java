package main.java.com.ingsoft.abarroteria.kinal.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.ingsoft.abarroteria.kinal.controller.LoginController;
import main.java.com.ingsoft.abarroteria.kinal.repository.AuthRepository;
import main.java.com.ingsoft.abarroteria.kinal.service.AuthService;

public class SceneManager {
    
    //atributos
    private final Stage stage; 
    
    //constructor 
    public SceneManager(Stage stage){
        this.stage = stage; 
    }
    
    // metodos
    public void showLoginView()throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login-view.fxml"));
        
        loader.setControllerFactory(
        clazz ->{
          if(clazz == LoginController.class){
             AuthRepository authRepository = new AuthRepository();
             AuthService authService = new AuthService(authRepository);
             return new LoginController(authService, this); 
          }
          try{
              return clazz.getDeclaredConstructor().newInstance();
          }catch(Exception e){
            throw new RuntimeException("error al crear el constructor" + e.getMessage());  
          }
          
        });
        Parent root = loader.load(); 
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    //dashboard Stage
    
    
    
    // alerta modal reutilizable. 
    public void showAlertInfo(String head, String title, String content, AlertType type){
      Alert alert = new Alert(type);
      alert.initOwner(this.stage);
      alert.setHeaderText(head);
      alert.setTitle(title);
      alert.setContentText(content);
      alert.showAndWait(); 
    }
    
}
