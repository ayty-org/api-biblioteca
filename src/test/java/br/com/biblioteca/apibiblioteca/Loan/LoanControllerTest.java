package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.Loan;
import br.com.biblioteca.apibiblioteca.loan.services.DeleteLoan;
import br.com.biblioteca.apibiblioteca.loan.services.GetLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListPageLoan;
import br.com.biblioteca.apibiblioteca.loan.services.SaveLoan;
import br.com.biblioteca.apibiblioteca.loan.services.UpdateLoan;
import br.com.biblioteca.apibiblioteca.loan.v1.LoanControllerV1;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static br.com.biblioteca.apibiblioteca.Loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoanControllerV1.class)
@DisplayName("Valida funcionalidade do Controller Loan")
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetLoan getLoan;
    @MockBean
    private ListLoan listLoan;
    @MockBean
    private ListPageLoan listPageLoan;
    @MockBean
    private SaveLoan saveLoan;
    @MockBean
    private UpdateLoan updateLoan;
    @MockBean
    private DeleteLoan deleteLoan;

    @Test
    @DisplayName("Pesquisa emprestimos por id")
    void whenValidGetIdLoan_thenReturnsLoan() throws Exception { //pesquisa por Loan

        when(getLoan.find(1L)).thenReturn(createLoan().id(1L).build());

        mockMvc.perform(get("/v1/api/loan/{id}",1L)
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userApp.name", is("teste nome")))
                .andExpect(jsonPath("$.userApp.age", is(21)))
                .andExpect(jsonPath("$.userApp.fone", is("46356357")))
                .andExpect(jsonPath("$.books[0].title", is("teste title")))
                .andExpect(jsonPath("$.books[0].resume", is("teste resume")))
                .andExpect(jsonPath("$.books[0].isbn", is("teste isbn")))
                .andExpect(jsonPath("$.books[0].author", is("teste author")));
    }

    @Test
    @DisplayName("Pesquisa lista de emprestimo")
    void whenValidListLoan_thenReturnsLoan() throws Exception { //pesquisa todos os Loans

        when(listLoan.findAll()).thenReturn(Lists.newArrayList(
                createLoan().id(1L).build(), createLoan().id(2L).build()
        ));

        mockMvc.perform(get("/v1/api/loan")
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userApp.name", is("teste nome")))
                .andExpect(jsonPath("[0].userApp.age", is(21)))
                .andExpect(jsonPath("$[0].userApp.fone", is("46356357")))
                .andExpect(jsonPath("$[0].books[0].title", is("teste title")))
                .andExpect(jsonPath("$[0].books[0].resume", is("teste resume")))
                .andExpect(jsonPath("$[0].books[0].isbn", is("teste isbn")))
                .andExpect(jsonPath("$[0].books[0].author", is("teste author")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].userApp.name", is("teste nome")))
                .andExpect(jsonPath("$[1].userApp.age", is(21)))
                .andExpect(jsonPath("$[1].userApp.fone", is("46356357")))
                .andExpect(jsonPath("$[1].books[0].title", is("teste title")))
                .andExpect(jsonPath("$[1].books[0].resume", is("teste resume")))
                .andExpect(jsonPath("$[1].books[0].isbn", is("teste isbn")))
                .andExpect(jsonPath("$[1].books[0].author", is("teste author")));
    }


    @Test
    @DisplayName("Pesquisa emprestimo com paginação")
    void whenValidListPageLoan_thenReturnsLoanPage() throws Exception { //pesquisa todos os Loans com paginanação

        Page<Loan> loanPage = new PageImpl<>(Collections.singletonList(createLoan().id(1L).build()));

        when(listPageLoan.findPage(0,2)).thenReturn(loanPage);

        mockMvc.perform(get("/v1/api/loan/?page=0&size=2")
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].userApp.name", is("teste nome")))
                .andExpect(jsonPath("$.content[0].userApp.age", is(21)))
                .andExpect(jsonPath("$.content[0].userApp.fone", is("46356357")))
                .andExpect(jsonPath("$.content[0].books[0].title", is("teste title")))
                .andExpect(jsonPath("$.content[0].books[0].resume", is("teste resume")))
                .andExpect(jsonPath("$.content[0].books[0].isbn", is("teste isbn")))
                .andExpect(jsonPath("$.content[0].books[0].author", is("teste author")));
    }

    @Test
    @DisplayName("Sava um emprestimo")
    void whenValidSaveLoan_thenReturns201() throws Exception { //insere emprestimo
        mockMvc.perform(post("/v1/api/laon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("loanDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um emprestimo")
    void whenValidUpdateLoan_thenReturnsLoan() throws Exception { //atualiza un emprestimo
        mockMvc.perform(put("/v1/api/laon/{id}", 1L)
                .content(readJson("loanUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deleta livro")
    void whenValidDeleteLoan_thenReturns204() throws Exception{ // deleta emprestimo
        mockMvc.perform(delete("/v1/api/loan/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
