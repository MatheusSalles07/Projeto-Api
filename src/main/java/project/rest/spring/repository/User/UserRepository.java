package project.rest.spring.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.rest.spring.model.Usuario.UsuarioModel;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UsuarioModel, Integer> {

    @Query (value = "SELECT * FROM Usuario", nativeQuery = true)
    List<UsuarioModel> listaDeUsuario();

    @Query (value = "SELECT * FROM Usuario WHERE cpf = :cpf", nativeQuery = true)
    Optional<UsuarioModel> findByCpf (@Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario set nome = :nome, email = :email " +
            "where cpf = :cpf ",nativeQuery = true)
    void update(@Param("nome") String nome,
                       @Param("email") String email,
                       @Param("cpf")String cpf);


    @Modifying
    @Transactional
    @Query(value = "insert into Usuario (nome, cpf ,email)  values (:name, :cpf, :email) ",nativeQuery = true)
    void  saveUser(@Param("name")String name,
                                 @Param("cpf")String cpf,
                                 @Param("email")String email);

}


