package com.itvedant.docmind;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocMindApplicationTests {

    // Basic smoke test. Database integration can be tested after local MySQL is running.
    @Test
    void applicationClassIsPresent() {
        assertNotNull(DocMindApplication.class);
    }
}
