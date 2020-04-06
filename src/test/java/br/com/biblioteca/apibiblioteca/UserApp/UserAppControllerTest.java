package br.com.biblioteca.apibiblioteca.UserApp;

import br.com.biblioteca.apibiblioteca.book.v1.BookControllerV1;
import br.com.biblioteca.apibiblioteca.user.services.DeleteUserApp;
import br.com.biblioteca.apibiblioteca.user.services.GetUserApp;
import br.com.biblioteca.apibiblioteca.user.services.ListPageUserApp;
import br.com.biblioteca.apibiblioteca.user.services.ListUserApp;
import br.com.biblioteca.apibiblioteca.user.services.SaveUserApp;
import br.com.biblioteca.apibiblioteca.user.services.UpdateUserApp;
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
@WebMvcTest(controllers = BookControllerV1.class)
@DisplayName("Valida funcionalidade do Controller de UserApp")
public class UserAppControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetUserApp getUserApp;
    @MockBean
    private ListUserApp listUserApp;
    @MockBean
    private ListPageUserApp listPageUserApp;
    @MockBean
    private SaveUserApp saveUserApp;
    @MockBean
    private UpdateUserApp updateUserApp;
    @MockBean
    private DeleteUserApp deleteUserApp;

    @Test
    @DisplayName("Pesquisa usuário por id")
    void whenValidGetIdUserApp_thenReturnsUserApp() throws Exception { //pesquisa por UserApp
        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/user/{id}",1)
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.UserAppID").value(1L));
    }

    @Test
    @DisplayName("Pesquisa lista de usuários")
    void whenValidListUserApp_thenReturnsUserApp() throws Exception { //pesquisa todos os UserApp

        mockMvc.perform( MockMvcRequestBuilders
                .get("/v1/api/user")
                .accept(MediaType.APPLICATION_JSON ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userApp[*].userAppID").isNotEmpty());
    }


    @Test
    @DisplayName("Pesquisa usuários com paginação")
    void whenValidListPageUserApp_thenReturnsUserAppPage() throws Exception { //pesquisa todos os UserApp com paginapção
        mockMvc.perform(get("/v1/api/user/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("UserAppDTO.json"));
    }

    @Test
    @DisplayName("Sava um usuário")
    void whenValidSaveUserApp_thenReturns201() throws Exception { //insere UserApp
        mockMvc.perform(post("/v1/api/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString("UserAppDTO.json")))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Edita um usuário")
    void whenValidUpdateUserApp_thenReturnsUserApp() throws Exception { //pesquisa todos os UserApp
        mockMvc.perform(put("/v1/api/user/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json("UserAppDTO.json"));
    }

    @Test
    @DisplayName("Deleta usuário")
    void whenValidDeleteUserApp_thenReturns204() throws Exception{ // deleta UserApp

        mockMvc.perform(delete("/v1/api/user/{id}",1L)
                .contentType("aplication/json")
                .content(objectMapper.writeValueAsString("UserAppDTO.json")))
                .andExpect(status().is(204));
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
