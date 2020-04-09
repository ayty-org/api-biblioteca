package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.exceptions.UserAppNotFoundException;
import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserAppServiceImpl implements GetUserAppService {

    private final UserAppRepository userAppRepository;

    @Override
    public UserApp find(Long id) {
        return userAppRepository.findById(id).orElseThrow(UserAppNotFoundException::new);
    }
}
