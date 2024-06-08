package br.com.globalsolution.economiaazul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.globalsolution.economiaazul.model.UserModel;
import br.com.globalsolution.economiaazul.model.RespostaModel;
import br.com.globalsolution.economiaazul.repositorio.UserRepositorio;

@Service
public class UserService {

    @Autowired
    private UserRepositorio userRepo;

    @Autowired
    private RespostaModel respostaModel;

    public ResponseEntity<?> autenticar(String username, String password) {
        UserModel user = userRepo.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            respostaModel.setMensagem("Usuário ou senha inválidos!");
            return new ResponseEntity<>(respostaModel, HttpStatus.UNAUTHORIZED);
        } else {
            respostaModel.setMensagem("Login realizado com sucesso!");
            return new ResponseEntity<>(respostaModel, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> cadastrar(UserModel userModel) {
        UserModel existingUser = userRepo.findByUsername(userModel.getUsername());
        if (existingUser != null) {
            respostaModel.setMensagem("Nome de usuário já existe!");
            return new ResponseEntity<>(respostaModel, HttpStatus.BAD_REQUEST);
        }

        userRepo.save(userModel);
        respostaModel.setMensagem("Usuário cadastrado com sucesso!");
        return new ResponseEntity<>(respostaModel, HttpStatus.CREATED);
    }
}
