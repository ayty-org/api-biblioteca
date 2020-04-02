package br.com.biblioteca.apibiblioteca.user.v1;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppDTO;
import br.com.biblioteca.apibiblioteca.user.services.DeleteUserApp;
import br.com.biblioteca.apibiblioteca.user.services.GetUserApp;
import br.com.biblioteca.apibiblioteca.user.services.ListPageUserApp;
import br.com.biblioteca.apibiblioteca.user.services.ListUserApp;
import br.com.biblioteca.apibiblioteca.user.services.SaveUserApp;
import br.com.biblioteca.apibiblioteca.user.services.UpdateUserApp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/user")
public class UserAppControllerV1 {

    private final GetUserApp getUserApp;
    private final ListUserApp listUserApp;
    private final ListPageUserApp listPageUserApp;
    private final SaveUserApp saveUserApp;
    private final UpdateUserApp updateUserApp;
    private final DeleteUserApp deleteUserApp;

    @GetMapping(value = "/{id}") //lista usuário por id
    public UserAppDTO find(@PathVariable Long id) {
        return UserAppDTO.from(getUserApp.find(id));
    }

    @GetMapping //lista todos os usuários
    public List<UserAppDTO> findAll() {
        return UserAppDTO.fromAll(listUserApp.findAll());
    }

    @GetMapping(params = {"page", "size"}) //lista todas os usuários com paginação
    public Page<UserAppDTO> findPage(@RequestParam Map<Integer, Integer> allParams) {
        return UserAppDTO.fromPage(listPageUserApp.findPage(allParams.get(0), allParams.get(2)));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um usuário
    public void insert(@Valid @RequestBody UserAppDTO userAppDTO) {
        saveUserApp.insert(UserApp.to(userAppDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualiza usuário
    public void update(@Valid @RequestBody UserAppDTO userAppDTO, @PathVariable Long id) {
        updateUserApp.update(UserApp.to(userAppDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta usuário
    public void delete(@PathVariable Long id) {
        deleteUserApp.delete(id);
    }
}
