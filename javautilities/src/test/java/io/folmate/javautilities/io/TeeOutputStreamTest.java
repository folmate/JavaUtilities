package io.folmate.javautilities.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TeeOutputStreamTest {
    @Test
    public void testNull(){
        assertDoesNotThrow(()-> {
            new TeeOutputStream(null, null);
        });
    }
}
