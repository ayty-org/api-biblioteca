package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserAppImpl implements UpdateUserApp {

    private final UserAppRepository userAppRepository;
    private final FindUserApp findUserApp;

    @Override
    public void update (UserApp obj){
        UserApp newObj = findUserApp.find(obj.getId());
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setAge(obj.getAge());
        newObj.setFone(obj.getFone());
        userAppRepository.save(newObj);
    }
}
