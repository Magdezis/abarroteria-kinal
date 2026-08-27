/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.com.ingsoft.abarroteria.kinal.controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.com.ingsoft.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.ingsoft.abarroteria.kinal.dto.response.LoginDTOResponse;
import main.java.com.ingsoft.abarroteria.kinal.service.AuthService;
import main.java.com.ingsoft.abarroteria.kinal.util.SceneManager;

/**
 * FXML Controller class
 *
 * @author Magno Solis
 */
public class LoginController implements Initializable {
    //atributos 
    private final AuthService authService;
    private final SceneManager sceneManager; 
    @FXML
    private Button btnIniciarSesion ; 
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPassword;
    
    // constructor 
    public LoginController(AuthService authService, SceneManager sceneManager){
        this.authService = authService;
        this.sceneManager = sceneManager; 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void handleLogin()throws Exception{
       if(txtFieldEmail.getText().isEmpty() || txtFieldPassword.getText().isEmpty()){
         sceneManager.showAlertInfo("Hay campos sin llenar", "No puedes dejar espacios en blanco.", "Intenta de nuevo.", Alert.AlertType.INFORMATION);
       }else{ 
           try{
            LoginDTOResponse response =  authService.login(new LoginDTORequest(txtFieldEmail.getText(), txtFieldPassword.getText()));
            sceneManager.showAlertInfo("Bienvenido: " + response.getNombre(),"Es bueno verte: ","Inicio de sesión correcto.", Alert.AlertType.INFORMATION);
            sceneManager.showDashboardView();
           }catch(RuntimeException e){
               sceneManager.showAlertInfo("Error al iniciar sesión.", "Verificar campos","No se ha podido iniciar sesión", Alert.AlertType.WARNING);
           }
         
       }
    }
    
}
