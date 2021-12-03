package project.rest.spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.rest.spring.model.Usuario.UsuarioModel;
import project.rest.spring.repository.User.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<UsuarioModel> usuarioModelList() {

        return userRepository.listaDeUsuario();

    }

    public UsuarioModel buscaPorCpf(String cpf) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF not Found"));

    }

    public ResponseEntity<Object> save(UsuarioModel usuarioModel){

        userRepository.saveUser(usuarioModel.getNome(),
                usuarioModel.getCpf(),
               usuarioModel.getEmail());

        return ResponseEntity.ok("Usuario Inserido");
    }

    public ResponseEntity<Object> update(UsuarioModel usuario, String cpf) {

        try {

        UsuarioModel user = buscaPorCpf(cpf);

        if (user.getCpf()!= null) {

            userRepository.update(
                    usuario.getNome(),
                    usuario.getEmail(),
                    user.getCpf());

            return ResponseEntity.ok("Atualizado com Sucesso!");
        }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
