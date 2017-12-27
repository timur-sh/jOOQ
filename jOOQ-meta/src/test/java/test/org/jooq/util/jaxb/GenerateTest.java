package test.org.jooq.util.jaxb;

import org.jooq.util.jaxb.Generate;
import org.jooq.util.jaxb.JpaVersion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateTest {
    @Test
    public void manualConfigure() {
        Generate generate = new Generate();
        assertNull(generate.getJpaVersion());

        generate.setJpaVersion(JpaVersion.V_2_2);
        assertEquals(generate.getJpaVersion(), JpaVersion.V_2_2);

        generate.withJpaVersion(JpaVersion.V_2_1);
        assertEquals(generate.getJpaVersion(), JpaVersion.V_2_1);
    }
}