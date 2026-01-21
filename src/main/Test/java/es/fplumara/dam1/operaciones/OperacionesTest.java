package es.fplumara.dam1.operaciones;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class OperacionesTest {

    @ParameterizedTest
    @CsvSource({
            "0.0, INSUFICIENTE",
            "4.99, INSUFICIENTE",
            "5.0, APROBADO",
            "6.99, APROBADO", "7.0, NOTABLE",
            "8.99, NOTABLE",
            "9.0, SOBRESALIENTE",
            "10.0, SOBRESALIENTE"
    })
    @DisplayName("Valid grades")
    void validGrades(double nota, String esperado) {
        assertEquals(esperado, Operaciones.calificacion(nota));
    }

    @Test
    @DisplayName("Calificaciones invalida")
    void notValidGrades() {
        assertThrows(IllegalArgumentException.class, () -> Operaciones.calificacion(-0.01));
        assertThrows(IllegalArgumentException.class, () -> Operaciones.calificacion(10.01));
    }
}
