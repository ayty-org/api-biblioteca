package br.com.biblioteca.apibiblioteca.user.v1;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppDTO;
import br.com.biblioteca.apibiblioteca.user.services.DeleteUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.FindAllUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.FindPageUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.FindUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.SaveUserAppImpl;
import br.com.biblioteca.apibiblioteca.user.services.UpdateUserAppImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/user")
public class UserAppControllerV1 {

    private final FindUserAppImpl findUserAppImpl;
    private final FindAllUserAppImpl findAllUserAppImpl;
    private final FindPageUserAppImpl findPageUserAppImpl;
    private final SaveUserAppImpl saveUserAppImpl;
    private final UpdateUserAppImpl updateUserAppImpl;
    private final DeleteUserAppImpl deleteUserAppImpl;

    @GetMapping(value = "/{id}") //lista usuário por id
    public UserAppDTO find(@PathVariable Long id) {
        return UserAppDTO.from(findUserAppImpl.find(id));
    }

    @GetMapping //lista todos os usuários
    public List<UserAppDTO> findAll() {
        return UserAppDTO.fromAll(findAllUserAppImpl.findAll());
    }

    @GetMapping(value = "/page") //lista todas os usuários com paginação
    public Page<UserAppDTO> findPage() {
        return UserAppDTO.fromPage(findPageUserAppImpl.findPage());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um usuário
    public void insert(@Valid @RequestBody UserAppDTO userAppDTO) {
        saveUserAppImpl.insert(UserApp.to(userAppDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualiza usuário
    public void update(@Valid @RequestBody UserAppDTO userAppDTO, @PathVariable Long id) {
        updateUserAppImpl.update(UserApp.to(userAppDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta usuário
    public void delete(@PathVariable Long id) {
        deleteUserAppImpl.delete(id);
    }
}
