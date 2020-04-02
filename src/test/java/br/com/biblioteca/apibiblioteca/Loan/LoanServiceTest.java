package br.com.biblioteca.apibiblioteca.Loan;

/*
@ExtendWith(MockitoExtension.class)
@Tag("service")
@DisplayName("Valida funcionalidade do serviço responsável por gerenciar emprestimos")
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    private SaveLoanImpl insertLoan;

    private FindLoanImpl findLoan;

    @BeforeEach
    public void setUp() {
        this.insertLoan = new SaveLoanImpl(loanRepository);
        this.findLoan = new FindLoanImpl(loanRepository);
    }

    @Test
    @DisplayName("Deve criar um emprestimo")
    void shouldCreateLoan() {

        //execução
        insertLoan.insert(createLoan().build());

        //preparação
        ArgumentCaptor<Loan> captorLoan = ArgumentCaptor.forClass(Loan.class);
        verify(loanRepository).save(captorLoan.capture());

        Loan result = captorLoan.getValue();

        //verificação
        assertAll("loan",
                () -> MatcherAssert.assertThat(result.getUserApp().getName(), is("teste nome")),
                () -> MatcherAssert.assertThat(result.getUserApp().getAge(), is(21)),
                () -> MatcherAssert.assertThat(result.getUserApp().getFone(), is("46356357")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getAuthor(), is("teste author")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getResume(), is("teste resume")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getIsbn(), is("teste isbn")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getTitle(), is("teste title")),
                () -> MatcherAssert.assertThat(result.getLoanTime(), is("50 dias"))
        );
    }

    @Test
    @DisplayName("Deve retornar um loan")
    void shouldFindById() {
        when(loanRepository.findById(anyLong())).thenReturn(
                Optional.of(createLoan().loanTime("loanTime Teste GET").build())
        );

        Loan result = this.findLoan.find(1L);

        //verificação
        assertAll("loan",
                () -> MatcherAssert.assertThat(result.getUserApp().getName(), is("teste nome")),
                () -> MatcherAssert.assertThat(result.getUserApp().getAge(), is(21)),
                () -> MatcherAssert.assertThat(result.getUserApp().getFone(), is("46356357")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getAuthor(), is("teste author")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getResume(), is("teste resume")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getIsbn(), is("teste isbn")),
                () -> MatcherAssert.assertThat(result.getBooks().get(0).getTitle(), is("teste title")),
                () -> MatcherAssert.assertThat(result.getLoanTime(), is("loanTime Teste GET"))
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando o emprestimo não for encontrado")
    void shouldThrowLoanNotFoundException() {
        when(loanRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(LoanNotFoundException.class, () -> this.findLoan.find(1L));
    }


}
 */
