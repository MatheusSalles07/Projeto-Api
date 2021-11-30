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

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioModel>> listResponseEntity(){
        return ResponseEntity.ok(userService.usuarioModelList());
    }

    @GetMapping("/cpf")
    public ResponseEntity<Optional<UsuarioModel>> findByCpf(@Param("cpf")String cpf){
        return ResponseEntity.ok(userService.buscaPorCpf(cpf));
    }

    @PostMapping
    @RequestMapping(path = "/insert")
    public ResponseEntity<Object> insert(@RequestBody UsuarioModel usuarioModel){

        return ResponseEntity.ok(userService.save(usuarioModel));

    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody UsuarioModel usuarioModel, @Param("cpf")String cpf){

        return ResponseEntity.ok(userService.save(usuarioModel));
    }

    @DeleteMapping(path = "/delete")
    public String delete(@Param("cpf")String cpf){
        ResponseEntity.ok(userService.delete(cpf));
        return "Usuario Deletado com Sucesso !!! ";
    }

}
