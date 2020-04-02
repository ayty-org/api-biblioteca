package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.exceptions.UserAppNotFoundException;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateUserAppImpl implements UpdateUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public void update(UserApp obj, Long id) {
        if (userAppRepository.findById(id).isPresent()) {
            obj.setId(id);
            userAppRepository.save(obj);
        }throw new UserAppNotFoundException();
    }
}
