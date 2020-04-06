package br.com.biblioteca.apibiblioteca.Loan;

import br.com.biblioteca.apibiblioteca.loan.services.DeleteLoan;
import br.com.biblioteca.apibiblioteca.loan.services.GetLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListLoan;
import br.com.biblioteca.apibiblioteca.loan.services.ListPageLoan;
import br.com.biblioteca.apibiblioteca.loan.services.SaveLoan;
import br.com.biblioteca.apibiblioteca.loan.services.UpdateLoan;
import br.com.biblioteca.apibiblioteca.loan.v1.LoanControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoanControllerV1.class)
@DisplayName("Valida funcionalidade do Controller de Loan")
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
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
        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/loan/{id}",1)
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.loanID").value(1L));
    }

    @Test
    @DisplayName("Pesquisa lista de emprestimo")
    void whenValidListLoan_thenReturnsLoan() throws Exception { //pesquisa todos os Loans

        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/loan")
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.loan").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.laon[*].loanID").isNotEmpty());
    }


    @Test
    @DisplayName("Pesquisa emprestimo com paginação")
    void whenValidListPageLoan_thenReturnsLoanPage() throws Exception { //pesquisa todos os Loans com paginanação
        mockMvc.perform(get("/v1/api/loan/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("laonDTO.json"));
    }

    @Test
    @DisplayName("Sava um emprestimo")
    void whenValidSaveLoan_thenReturns201() throws Exception { //insere emprestimo
        mockMvc.perform(post("/v1/api/loan")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("LoanDTO.json")))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um emprestimo")
    void whenValidUpdateLoan_thenReturnsLoan() throws Exception { //atualiza un emprestimo
        mockMvc.perform(put("/v1/api/loan/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("LoanDTO.json"));
    }

    @Test
    @DisplayName("Deleta livro")
    void whenValidDeleteLoan_thenReturns204() throws Exception{ // deleta emprestimo

        mockMvc.perform(delete("/v1/api/loan/{id}",1L)
                .contentType("aplication/json")
                .content(objectMapper.writeValueAsString("LoanDTO.json")))
                .andExpect(status().is(204));
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
