package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InsertUserAppImpl implements InsertUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public void insert(UserApp obj){
        userAppRepository.save(obj);
    }
}
