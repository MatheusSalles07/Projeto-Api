package project.rest.spring.Controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rest.spring.model.Usuario.UsuarioModel;
import project.rest.spring.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioModel>> listResponseEntity() {
        return ResponseEntity.ok(userService.usuarioModelList());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioModel> findByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(userService.buscaPorCpf(cpf));
    }

    @PostMapping
    @RequestMapping(path = "/insert")
    public ResponseEntity<Object> insert(@RequestBody UsuarioModel usuarioModel) {

        return ResponseEntity.ok(userService.save(usuarioModel));

    }

    @PutMapping(path = "/update/{cpf}")
    public ResponseEntity<Object> update(@RequestBody UsuarioModel usuarioModel, @PathVariable("cpf") String cpf) {

        return userService.update(usuarioModel, cpf);
    }

    @DeleteMapping(path = "/delete/{cpf}")
    public String delete(@PathVariable("cpf") String cpf) {
        userService.delete(cpf);
        return "Usuario Deletado! ";

    }
}
