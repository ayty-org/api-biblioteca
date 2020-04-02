package br.com.biblioteca.apibiblioteca.UserApp;

/*
@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por gerenciar UserApp")
public class UserAppServiceTest {

    @Mock
    private UserAppRepository userAppRepository;

    private SaveUserAppImpl insertUserApp;

    private FindUserApp findUserApp;

    @BeforeEach
    public void setUp() {
        this.insertUserApp = new SaveUserAppImpl(userAppRepository);
        this.findUserApp = new FindUserAppImpl(userAppRepository);
    }

    @Test
    @DisplayName("Deve criar um usuário")
    public void shouldCreateBook(){
        //execução
        insertUserApp.insert(createUserApp().build());

        //preparação
        ArgumentCaptor<UserApp> captorUserApp = ArgumentCaptor.forClass(UserApp.class);
        verify(userAppRepository).save(captorUserApp.capture());

        UserApp result = captorUserApp.getValue();

        //verificação
        assertAll("book",
                () -> MatcherAssert.assertThat(result.getName(), is("teste nome")),
                () -> MatcherAssert.assertThat(result.getAge(), is(20)),
                () -> MatcherAssert.assertThat(result.getFone(), is("teste fone"))
        );
    }

    @Test
    @DisplayName("Deve retornar um usuário")
    void shouldFindById() {
        when(userAppRepository.findById(anyLong())).thenReturn(
                Optional.of(createUserApp().name("Nome Teste GET").build())
        );

        UserApp result = this.findUserApp.find(1L);

        //verificação
        assertAll("book",
                () -> MatcherAssert.assertThat(result.getName(), is("Nome Teste GET")),
                () -> MatcherAssert.assertThat(result.getAge(), is(20)),
                () -> MatcherAssert.assertThat(result.getFone(), is("teste fone"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o usuário não for encontrado")
    void shouldThrowUserAppNotFoundException() {
        when(userAppRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserAppNotFoundException.class, () -> this.findUserApp.find(1L));
    }
}

 */
