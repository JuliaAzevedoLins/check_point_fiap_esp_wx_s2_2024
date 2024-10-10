package br.com.fiap.twoespwx.libunclepresser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import br.com.fiap.twoespwx.libunclepresser.App;

public class AppTest {

    @Test
    public void testCompressData() {
        // Testa a compressão de uma sequência de caracteres
        assertEquals("A4C3T2G1", App.compressData("AAAACCCTTG"));
        assertEquals("G2A2C2T2C2", App.compressData("GGAACCTTCC"));
        assertEquals("G10", App.compressData("GGGGGGGGGG"));
        assertEquals("T1G8C1", App.compressData("TGGGGGGGGC"));
        
        // Testa a compressão de uma sequência vazia
        assertEquals("", App.compressData(""));
        
        // Testa a compressão de uma sequência com um único caractere
        assertEquals("A1", App.compressData("A"));
        
        // Testa a compressão de uma sequência sem caracteres repetidos
        assertEquals("A1B1C1D1", App.compressData("ABCD"));
    }
}
