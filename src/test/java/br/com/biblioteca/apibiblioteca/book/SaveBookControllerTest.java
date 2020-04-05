package br.com.biblioteca.apibiblioteca.book;

import br.com.biblioteca.apibiblioteca.book.services.*;
import br.com.biblioteca.apibiblioteca.book.v1.BookControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookControllerV1.class)
public class SaveBookControllerTest {

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
    void whenValidInsert_thenReturns201() throws Exception {

        BookDTO bookDTO = new BookDTO("teste title","teste resume","teste isbn","teste author",new Date());

        mockMvc.perform(post("/v1/api/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated());
    }
}
