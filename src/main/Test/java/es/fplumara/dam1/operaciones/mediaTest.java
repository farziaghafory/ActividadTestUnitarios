package es.fplumara.dam1.operaciones;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class mediaTest {

        // This provide test cases: array of notes and expected average
        static Stream<Object[]> mediaProvider() {
            return Stream.of(
                    new Object[]{new double[]{5.0, 7.0}, 6.0},
                    new Object[]{new double[]{10.0}, 10.0},
                    new Object[]{new double[]{0.0, 0.0, 0.0}, 0.0}
            );
        }

        @ParameterizedTest
        @MethodSource("mediaProvider")
        @DisplayName("Check media calculation with different notes")
        public void testMedia(double[] notas, double esperado) {
            // Calculate average and compare with expected
            assertEquals(esperado, Operaciones.media(notas), 0.0001);
        }

        @Test
        @DisplayName("Check multiple medias with assertAll")
        public void testMediaAssertAll() {
            assertAll(
                    () -> assertEquals(6.0, Operaciones.media(5, 7), 0.0001),
                    () -> assertEquals(10.0, Operaciones.media(10), 0.0001),
                    () -> assertEquals(0.0, Operaciones.media(0, 0, 0), 0.0001)
            );
        }

        // invalid input
        @Nested
        @DisplayName("Invalid media cases")
        class CasosInvalidos {

            @Test
            @DisplayName("Media with no notes should throw exception")
            void mediaSinNotas() {
                assertThrows(IllegalArgumentException.class, () -> Operaciones.media());
            }

            @Test
            @DisplayName("Media with null should throw exception")
            void mediaNotasNull() {
                assertThrows(IllegalArgumentException.class, () -> Operaciones.media((double[]) null));
            }
        }
    }
