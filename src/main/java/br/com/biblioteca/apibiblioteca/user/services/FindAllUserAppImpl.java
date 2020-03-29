package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllUserAppImpl implements FindAllUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public List<UserApp> findAll() {
        return userAppRepository.findAll();
    }
}
