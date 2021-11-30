package project.rest.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.rest.spring.model.Usuario.UsuarioModel;
import project.rest.spring.repository.User.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<UsuarioModel> usuarioModelList() {

        return userRepository.listaDeUsuario();

    }

    public Optional<UsuarioModel> buscaPorCpf(String Cpf) {
        return userRepository.findByCpf(Cpf);

    }

    public ResponseEntity<Object> save(UsuarioModel usuarioModel){

        userRepository.saveUser(usuarioModel.getNome(),
                usuarioModel.getCpf(),
               usuarioModel.getEmail());

        return ResponseEntity.ok("Usuario Inserido");
    }

    public ResponseEntity<Object> update(UsuarioModel usuarioModel, String cpf){
        try {

            UsuarioModel user = buscaPorCpf(cpf);

            if (user.getCpf()!= null){

                UserRepository.update(
                        usuarioModel.getName(),
                        usuarioModel.getEmail(),
                        user.getCpf()
                );
                return ResponseEntity.ok("Atualizado Com Sucesso !!! ");
            }
            
      

    }
}
