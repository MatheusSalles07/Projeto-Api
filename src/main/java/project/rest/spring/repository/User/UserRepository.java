package project.rest.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.rest.spring.model.Usuario;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <Usuario,Integer> {

    @Query (value = "SELECT * FROM Usuario", nativeQuery = true)
    List<Usuario> listaDeUsuario();
}
