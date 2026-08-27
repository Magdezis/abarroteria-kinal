/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.ingsoft.abarroteria.kinal.service;

import main.java.com.ingsoft.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.ingsoft.abarroteria.kinal.dto.response.LoginDTOResponse;
import main.java.com.ingsoft.abarroteria.kinal.repository.AuthRepository;
import main.java.com.ingsoft.abarroteria.kinal.security.jbcrypt.BCrypt;

/**
 *
 * @author Magno Solis
 */
public class AuthService {

    // atributos
    private final AuthRepository authRepository;

    //constructor 
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    //metodo
    public LoginDTOResponse login(LoginDTORequest loginDTORequest) {
        if (loginDTORequest == null) {
            throw new RuntimeException("Los datos estan vacios");
        } else if (loginDTORequest.getEmail() == null || loginDTORequest.getPassword() == null) {
            throw new RuntimeException("Uno o los dos campos estan vacios.");
        } else if (loginDTORequest.getEmail().isEmpty() || loginDTORequest.getPassword().isEmpty()) {
            throw new RuntimeException("No puedes dejar campos en blanco");
        }

        LoginDTOResponse response = authRepository.findUserByEmail(loginDTORequest);
        
        if(response == null){
            throw new RuntimeException("Usuario no encontrado.");
        }
        
        System.out.println("dato en servicio: " + response.getNombre());
        if (response.getContrasenaHash() == null) {
            throw new RuntimeException("no se ha podido concretar la operación.");
        } else {
            if (BCrypt.checkpw(loginDTORequest.getPassword(), response.getContrasenaHash())) {
                return response; 
               
            }
        }
        return null;
    }

}
