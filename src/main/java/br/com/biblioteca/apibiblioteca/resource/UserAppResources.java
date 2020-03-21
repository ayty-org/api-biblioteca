package br.com.biblioteca.apibiblioteca.resource;

import br.com.biblioteca.apibiblioteca.domain.UserApp;
import br.com.biblioteca.apibiblioteca.dto.UserAppDTO;
import br.com.biblioteca.apibiblioteca.service.UserAppService;
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
public class UserAppResources {

    @Autowired
    private UserAppService userAppService;

    @GetMapping //lista todos os usuários
    public ResponseEntity<List<UserAppDTO>> findAll() {
        List<UserApp> list = userAppService.findAll();
        List<UserAppDTO> listDto = list.stream().map(obj -> new UserAppDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{id}") //lista usuário por id
    public ResponseEntity<UserApp> find(@PathVariable Long id){
        UserApp obj = userAppService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page") //lista todas os usuários com paginação
    public ResponseEntity<Page<UserAppDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<UserApp> list = userAppService.findPage(page, linesPerPage, orderBy, direction);
        Page<UserAppDTO> listDto = list.map(obj -> new UserAppDTO(obj));
        return ResponseEntity.ok().body(listDto);
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
