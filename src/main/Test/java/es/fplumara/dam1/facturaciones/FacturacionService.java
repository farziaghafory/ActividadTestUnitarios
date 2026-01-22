package es.fplumara.dam1.facturaciones;

import es.fplumara.dam1.facturacion.Calculadora;
import es.fplumara.dam1.facturacion.FacturaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //inicializar los mocks automáticamente
class FacturaServiceTest {

    @Mock
    Calculadora calc;


    @Test
    @DisplayName("totalConIva usando mock") //nombre humano en el reporte.
    void testTotalConIva() {
        FacturaService service = new FacturaService(calc);
        when(calc.sumar(eq(100), anyInt())).thenReturn(121); // can do anyinput() intstead of 21 to put anything but still return 121
        when(calc.sumar(0, 21)).thenReturn(21);// the bonus

        int res1 = service.totalConIva(100);
        int res2 = service.totalConIva(0);
        assertEquals(121, res1);
        assertEquals(21, res2);


        verify(calc).sumar(100, 21);
        verify(calc).sumar(0, 21);//
        verifyNoMoreInteractions(calc);
    }
}