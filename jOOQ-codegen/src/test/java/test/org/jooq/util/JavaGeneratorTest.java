package test.org.jooq.util;

import org.jooq.util.JavaGenerator;
import org.jooq.util.jaxb.JpaVersion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaGeneratorTest {
    @Test
    public void manualConfiguration() {
        JavaGenerator generator = new JavaGenerator();
        assertNull(generator.generateJpaVersion());

        generator.setGenerateJpaVersion(JpaVersion.V_2_0);
        assertEquals(generator.generateJpaVersion(), JpaVersion.V_2_0);
    }
}