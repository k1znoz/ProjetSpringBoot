package fr.cda.ecole.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.cda.ecole.dto.UtilisateurDto;
import fr.cda.ecole.entity.Role;
import fr.cda.ecole.entity.Utilisateur;
import fr.cda.ecole.repository.UtilisateurRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    void save_shouldHashPasswordBeforeSaving() {
        String plainPassword = "motDePasseClair";
        String hashedPassword = new BCryptPasswordEncoder().encode(plainPassword);

        UtilisateurDto input = new UtilisateurDto();
        input.setUsername("user1");
        input.setPasswordHash(plainPassword);
        input.setActif(Boolean.TRUE);
        input.setRole("ADMIN");

        when(passwordEncoder.encode(plainPassword)).thenReturn(hashedPassword);
        when(utilisateurRepository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UtilisateurDto result = utilisateurService.save(input);

        ArgumentCaptor<Utilisateur> captor = ArgumentCaptor.forClass(Utilisateur.class);
        verify(passwordEncoder).encode(plainPassword);
        verify(utilisateurRepository).save(captor.capture());

        Utilisateur saved = captor.getValue();
        assertNotEquals(plainPassword, saved.getPasswordHash());
        assertEquals(hashedPassword, saved.getPasswordHash());
        assertTrue(new BCryptPasswordEncoder().matches(plainPassword, saved.getPasswordHash()));
        assertEquals(hashedPassword, result.getPasswordHash());
        assertEquals("user1", result.getUsername());
        assertEquals(Role.ADMIN.name(), result.getRole());
    }
}
