package fr.cda.ecole.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cda.ecole.dto.EleveDto;
import fr.cda.ecole.controller.AuthController;
import fr.cda.ecole.controller.EleveController;
import fr.cda.ecole.exception.GlobalExceptionHandler;
import fr.cda.ecole.entity.Role;
import fr.cda.ecole.entity.Utilisateur;
import fr.cda.ecole.repository.UtilisateurRepository;
import fr.cda.ecole.security.CustomUserDetailsService;
import fr.cda.ecole.security.JwtAuthenticationFilter;
import fr.cda.ecole.security.JwtService;
import fr.cda.ecole.security.dto.LoginRequest;
import fr.cda.ecole.service.EleveService;
import fr.cda.ecole.config.SecurityConfig;

@SpringBootTest(classes = EleveControllerIntegrationTest.TestApplication.class)
@AutoConfigureMockMvc
class EleveControllerIntegrationTest {

    @SpringBootConfiguration
    @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
    @Import({
            AuthController.class,
            EleveController.class,
            GlobalExceptionHandler.class,
            SecurityConfig.class,
            JwtAuthenticationFilter.class,
            JwtService.class,
            CustomUserDetailsService.class
    })
    static class TestApplication {
    }

    private static final String TEST_USERNAME = "admin";
    private static final String TEST_PASSWORD = "motdepasse123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EleveService eleveService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    private String jwtToken;

    @BeforeEach
    void setUp() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(TEST_USERNAME);
        utilisateur.setPasswordHash(passwordEncoder.encode(TEST_PASSWORD));
        utilisateur.setActif(Boolean.TRUE);
        utilisateur.setRole(Role.ADMIN);

        when(utilisateurRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(utilisateur));

        jwtToken = login(TEST_USERNAME, TEST_PASSWORD);
    }

    @Test
    void login_valid_shouldReturn200AndToken() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername(TEST_USERNAME);
        request.setPassword(TEST_PASSWORD);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void login_invalid_shouldReturn401() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername(TEST_USERNAME);
        request.setPassword("bad-password");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll_shouldReturnEleves() throws Exception {
        EleveDto eleve = buildEleveDto(1L, "Dupont", "Leo", "leo@ecole.fr");
        when(eleveService.findAll()).thenReturn(List.of(eleve));

        performApiRequest(get("/api/eleves/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idEleve").value(1))
                .andExpect(jsonPath("$[0].nom").value("Dupont"));
    }

    @Test
    void getById_shouldReturnEleve() throws Exception {
        EleveDto eleve = buildEleveDto(1L, "Dupont", "Leo", "leo@ecole.fr");
        when(eleveService.findById(1L)).thenReturn(Optional.of(eleve));

        performApiRequest(get("/api/eleves/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEleve").value(1))
                .andExpect(jsonPath("$.prenom").value("Leo"));
    }

    @Test
    void post_shouldCreateEleve() throws Exception {
        EleveDto input = buildEleveDto(null, "Martin", "Lina", "lina@ecole.fr");
        EleveDto saved = buildEleveDto(2L, "Martin", "Lina", "lina@ecole.fr");
        when(eleveService.save(any(EleveDto.class))).thenReturn(saved);

        performApiRequest(post("/api/eleves/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idEleve").value(2))
                .andExpect(jsonPath("$.nom").value("Martin"));
    }

    @Test
    void put_shouldUpdateEleve() throws Exception {
        EleveDto existing = buildEleveDto(1L, "Dupont", "Leo", "leo@ecole.fr");
        EleveDto updatePayload = buildEleveDto(99L, "Dupont", "Leon", "leo@ecole.fr");
        EleveDto updated = buildEleveDto(1L, "Dupont", "Leon", "leo@ecole.fr");

        when(eleveService.findById(1L)).thenReturn(Optional.of(existing));
        when(eleveService.save(any(EleveDto.class))).thenReturn(updated);

        performApiRequest(put("/api/eleves/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEleve").value(1))
                .andExpect(jsonPath("$.prenom").value("Leon"));
    }

    @Test
    void delete_shouldReturnNoContent() throws Exception {
        EleveDto existing = buildEleveDto(1L, "Dupont", "Leo", "leo@ecole.fr");
        when(eleveService.findById(1L)).thenReturn(Optional.of(existing));

        performApiRequest(delete("/api/eleves/1"))
                .andExpect(status().isNoContent());

        verify(eleveService).deleteById(1L);
    }

    private String login(String username, String password) throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        String responseBody = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("token").asText();
    }

    private org.springframework.test.web.servlet.ResultActions performApiRequest(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        return mockMvc.perform(requestBuilder.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken));
    }

    private EleveDto buildEleveDto(Long id, String nom, String prenom, String email) {
        EleveDto dto = new EleveDto();
        dto.setIdEleve(id);
        dto.setNom(nom);
        dto.setPrenom(prenom);
        dto.setDateNaissance(LocalDate.of(2013, 5, 14));
        dto.setAdresse("12 rue des Ecoles");
        dto.setEmail(email);
        dto.setTelephone("0611223344");
        return dto;
    }
}
