package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.DeleteBook;
import br.com.biblioteca.apibiblioteca.book.services.GetBook;
import br.com.biblioteca.apibiblioteca.book.services.ListBook;
import br.com.biblioteca.apibiblioteca.book.services.ListPageBook;
import br.com.biblioteca.apibiblioteca.book.services.SaveBook;
import br.com.biblioteca.apibiblioteca.book.services.UpdateBook;
import br.com.biblioteca.apibiblioteca.book.v1.BookControllerV1;
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

import static br.com.biblioteca.apibiblioteca.book.builders.BookBuilder.createBook;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookControllerV1.class)
@DisplayName("Valida funcionalidade do Controller de Book")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetBook getBook;
    @MockBean
    private ListBook listBook;
    @MockBean
    private ListPageBook listPageBook;
    @MockBean
    private SaveBook saveBook;
    @MockBean
    private UpdateBook updateBook;
    @MockBean
    private DeleteBook deleteBook;

    @Test
    @DisplayName("Pesquisa livro por id")
    void whenValidGetIdBook_thenReturnsBook() throws Exception { //pesquisa por livro
        saveBook.insert(createBook().id(1L).build());
        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/book/{id}",1)
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookID").value(1L));
    }

    @Test
    @DisplayName("Pesquisa lista de livros")
    void whenValidListBook_thenReturnsBook() throws Exception { //pesquisa todos os livro

        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/book")
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.book").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.book[*].bookID").isNotEmpty());
    }


    @Test
    @DisplayName("Pesquisa livro com paginação")
    void whenValidListPageBook_thenReturnsBookPage() throws Exception { //pesquisa todos os livro
        mockMvc.perform(get("/v1/api/book/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("BookDTO.json"));
    }

    @Test
    @DisplayName("Sava um livro")
    void whenValidSaveBook_thenReturns201() throws Exception { //insere livro
        mockMvc.perform(post("/v1/api/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("BookDTO.json")))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um livro")
    void whenValidUpdateBook_thenReturnsBook() throws Exception { //pesquisa todos os livro
        mockMvc.perform(put("/v1/api/book/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("BookDTO.json"));
    }

    @Test
    @DisplayName("Deleta livro")
    void whenValidDelete_thenReturns204() throws Exception{ // deleta livro

        mockMvc.perform(delete("/v1/api/book/{id}",1L)
                .contentType("aplication/json")
                .content(objectMapper.writeValueAsString("BookDTO.json")))
                .andExpect(status().is(204));
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
