package br.com.biblioteca.apibiblioteca.user.services;

import br.com.biblioteca.apibiblioteca.user.UserApp;
import br.com.biblioteca.apibiblioteca.user.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindPageUserAppImpl implements FindPageUserApp {

    private final UserAppRepository userAppRepository;

    @Override
    public Page<UserApp> findPage() {
        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.valueOf("ASC"), "title");
        return userAppRepository.findAll(pageRequest);
    }
}
