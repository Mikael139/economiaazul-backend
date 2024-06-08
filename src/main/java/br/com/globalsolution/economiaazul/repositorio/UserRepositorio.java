package br.com.globalsolution.economiaazul.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.globalsolution.economiaazul.model.UserModel;

@Repository
public interface UserRepositorio extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
