package org.example.authorization;

import org.example.authorization.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationTest {
    @Mock
    UserServiceImpl userService;

    @Mock
    HttpServletRequest req;

    private Authentication authentication;

    @BeforeEach
    void setUp() {


        authentication = new Authentication();
    }

/*    @Test
    void isAuthorizedTest_withCorrectAuth_expectTrue(){
        when(req.getHeader("Authorization")).thenReturn("Basic QW50b246MTIzNDU=");
        when(userService.countUsersByLogin(anyString(), anyString())).thenReturn(1L);

        assertTrue( authorization.isAuthorized(req, userService));
    }

    @Test
    void isAuthorizedTest_withNotExistingUser_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn("Basic QW50b246MTIzNDU=");
        when(userService.countUsersByLogin(anyString(), anyString())).thenReturn(0L);

        assertFalse( authorization.isAuthorized(req, userService));
    }*/

/*    @Test
    void isAuthorizedTest_withNoAuthorization_expectFalse(){
        when(req.getHeader("Authorization")).thenReturn(null);

        assertFalse( authorization.isAuthorized(req, userService));
    }*/
}