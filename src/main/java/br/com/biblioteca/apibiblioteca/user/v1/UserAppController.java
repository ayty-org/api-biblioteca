package br.com.biblioteca.apibiblioteca.user.v1;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppDTO;
import br.com.biblioteca.apibiblioteca.user.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/v1/api/user")
public class UserAppController {

    @Autowired
    private UserAppService userAppService;

    @GetMapping //lista todos os usuários
    public List<UserAppDTO> findAll() {
        List<UserApp> list = userAppService.findAll();
        List<UserAppDTO> listDto = list.stream().map(obj -> new UserAppDTO(obj)).collect(Collectors.toList());
        return listDto;
    }

    @GetMapping(value="/{id}") //lista usuário por id
    public UserApp find(@PathVariable Long id){
        UserApp userApp = userAppService.find(id);
        return userApp;
    }

    @GetMapping(value = "/page") //lista todas os usuários com paginação
    public Page<UserAppDTO> findPage(){
        Page<UserApp> list = userAppService.findPage();
        Page<UserAppDTO> listDto = list.map(obj -> new UserAppDTO(obj));
        return listDto;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping() //adiciona um usuário Book
    public void insert(@Valid @RequestBody UserAppDTO objDto){
        UserApp userApp = userAppService.fromDTO(objDto);
        userAppService.insert(userApp);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="/{id}")
    public void update(@Valid @RequestBody UserAppDTO objDto, @PathVariable Long id){
        UserApp userApp = userAppService.fromDTO(objDto);
        userApp.setId(id);
        userAppService.update(userApp);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}") //Deleta usuário
    public void delete(@PathVariable Long id){
        userAppService.delete(id);
    }
}
