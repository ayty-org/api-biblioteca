package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.exceptions.UserAppNotDeletedException;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserAppImpl implements DeleteUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public void delete(Long id) {
        if (!userAppRepository.existsById(id)) {
            throw new UserAppNotDeletedException();
        }
        userAppRepository.deleteById(id);
    }
}
