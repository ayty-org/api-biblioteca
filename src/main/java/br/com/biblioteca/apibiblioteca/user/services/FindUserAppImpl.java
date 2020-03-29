package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.exceptions.ObjectNotFoundException;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FindUserAppImpl implements FindUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public UserApp find(Long id) {
        Optional<UserApp> obj = userAppRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserApp.class.getName()));
    }
}
