package es.fplumara.dam1.coche;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CocheServiceTest {

    @Mock
    CocheRepository cocheRepository;

    @InjectMocks
    CocheService cocheService;

    // validaMatricula (tests parametrizados)
    @ParameterizedTest
    @ValueSource(strings = {"1234ABC", "0000ZZZ", "9876QWE"})
    @DisplayName("valid matricula")
    void testValidaMatriculaValida(String matricula) {
        assertTrue(cocheService.validaMatricula(matricula));
    }

    @ParameterizedTest
    @CsvSource({"123ABC", "12345ABC", "1234AB", "1234A1C", "1234-ABC", "1234 ABC", "1234abc"})
    @DisplayName("invalid matricula")
   void testMatriculaInvalida(String matricula){
        assertFalse(cocheService.validaMatricula(matricula));
    }


    //comprarCoche y buscarCoche
@Test
@DisplayName("buy valid car")
    void comprarCocheValida(){
        Coche coche = new Coche();
        coche.setMatricula("1234ABC");
        cocheService.comprarCoche(coche);
        verify(cocheRepository, times(1)).save(coche);

}
//invalid
    @Test
    @DisplayName("buy invalid car")
    void comprarCochesInvalida(){
        Coche coche = new Coche();
        coche.setMatricula("1234-ABC");
        assertThrows(IllegalArgumentException.class, () ->cocheService.comprarCoche(coche));
        verifyNoMoreInteractions(cocheRepository);

    }
    @Test
    @DisplayName("buscar Coche valid")
    void buscarCocheValida(){
        Coche coche = new Coche();
        coche.setMatricula("9999ZZZ");
        when(cocheRepository.findByMatricula("9999ZZZ")).thenReturn(coche);
        Coche resultado = cocheService.buscarCoche("9999ZZZ");

        assertEquals(coche, resultado);
        verify(cocheRepository, times(1)).findByMatricula("9999ZZZ");
    }


    @Test
    @DisplayName("buscar Coche Invalida")
    void buscarCocheInvalida(){
        Coche coche = new Coche();
        coche.setMatricula("123ABC");
        assertThrows(IllegalArgumentException.class, () ->cocheService.buscarCoche(coche.getMatricula()));
        verifyNoMoreInteractions(cocheRepository);

    }


}

















/*







    @Test
    @DisplayName("coche invalid")
    void testBuscarCocheInvalida() {
        assertThrows(IllegalArgumentException.class, () -> cocheService.buscarCoche("12AA"));
        verifyNoMoreInteractions(cocheRepository);
    }
}*/