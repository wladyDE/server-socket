package org.example.authentication;

import org.example.domain.User;
import org.example.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationTest {
    @Mock
    UserService userService;

    @Mock
    HttpServletRequest req;

    private Authentication authentication;

    private User user;

    private final static String LOGIN = "Wlady";

    private final static String PASSWORD = "1234";

/*    @BeforeEach
    void setUp() {
        authentication = new Authentication();

        user = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build();
    }*/

   /* @Test
    void isAuthorizedTest_withCorrectAuth_expectTrue(){
        when(req.getHeader("Authorization")).thenReturn("Basic QW50b246MTIzNDU=");
        when(userService.findByLogin(anyString())).thenReturn(user);

        try (MockedStatic<PasswordHasher> passwordHasherMock = mockStatic(PasswordHasher.class);
             MockedStatic<BasicAuthDecoder> basicAuthDecoderMock = mockStatic(BasicAuthDecoder.class)) {

            passwordHasherMock.when(() -> PasswordHasher.verifyPassword(anyString(), anyString())).thenReturn(true);
            passwordHasherMock.when(() -> PasswordHasher.hashPassword(anyString(), anyString())).thenReturn(PASSWORD);
            basicAuthDecoderMock.when(() -> BasicAuthDecoder.decodeBasicAuth(anyString())).thenReturn(LOGIN + ":" + PASSWORD);

            assertTrue( authentication.isAuthenticated(req, userService));
        }
    }

    @Test
    void isAuthorizedTest_withNoAuthorization_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn(null);

        assertFalse( authentication.isAuthenticated(req, userService));
    }

    @Test
    void isAuthorizedTest_withNoAuthorization2_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn("Hello World");

        assertFalse( authentication.isAuthenticated(req, userService));
    }

    @Test
    void isAuthorizedTest_withWrongCredentials_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn("Basic QW50b246MTIzNDU=");
        when(userService.findByLogin(anyString())).thenReturn(user);

        try (MockedStatic<PasswordHasher> passwordHasherMock = mockStatic(PasswordHasher.class);
             MockedStatic<BasicAuthDecoder> basicAuthDecoderMock = mockStatic(BasicAuthDecoder.class)) {

            passwordHasherMock.when(() -> PasswordHasher.verifyPassword(anyString(), anyString())).thenReturn(true);
            passwordHasherMock.when(() -> PasswordHasher.hashPassword(anyString(), anyString())).thenReturn(PASSWORD);
            basicAuthDecoderMock.when(() -> BasicAuthDecoder.decodeBasicAuth(anyString())).thenReturn(LOGIN + PASSWORD);

            assertFalse( authentication.isAuthenticated(req, userService));
        }
    }

    @Test
    void isAuthorizedTest_withNotExistingUser_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn("Basic QW50b246MTIzNDU=");
        when(userService.findByLogin(anyString())).thenReturn(null);

        try (MockedStatic<PasswordHasher> passwordHasherMock = mockStatic(PasswordHasher.class);
             MockedStatic<BasicAuthDecoder> basicAuthDecoderMock = mockStatic(BasicAuthDecoder.class)) {

            passwordHasherMock.when(() -> PasswordHasher.verifyPassword(anyString(), anyString())).thenReturn(true);
            passwordHasherMock.when(() -> PasswordHasher.hashPassword(anyString(), anyString())).thenReturn(PASSWORD);
            basicAuthDecoderMock.when(() -> BasicAuthDecoder.decodeBasicAuth(anyString())).thenReturn(LOGIN + ":" + PASSWORD);

            assertFalse( authentication.isAuthenticated(req, userService));
        }
    }*/
}