package br.com.globalsolution.economiaazul.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.globalsolution.economiaazul.model.UserModel;
import br.com.globalsolution.economiaazul.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel user) {
        return userService.autenticar(user.getUsername(), user.getPassword());
    }

    @PostMapping("/cadastrarUsuarios")
    public ResponseEntity<?> cadastrar(@RequestBody UserModel user) {
        return userService.cadastrar(user);
    }
}
