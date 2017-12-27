package test.org.jooq.util.jaxb;

import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generate;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.JpaVersion;
import org.jooq.util.jaxb.Strategy;
import org.jooq.util.jaxb.Target;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {
    @Test
    public void configuration() {

        Configuration configuration = new Configuration()
           .withGenerator(new Generator()
              .withName("org.jooq.util.DefaultGenerator")
              .withStrategy(new Strategy().withName("org.jooq.util.DefaultGeneratorStrategy"))
              .withDatabase(new Database()
                 .withName("org.jooq.util.postgres.PostgresDatabase")
                 .withInputSchema("public"))
              .withGenerate(new Generate()
                 .withRelations(true)
                 .withDeprecated(false)
                 .withRecords(true)
                 .withImmutablePojos(false)
                 .withPojos(true)
                 .withFluentSetters(true)
                 .withJpaVersion(JpaVersion.V_2_2)
                 .withFluentSetters(false))

              .withTarget(new Target()
                 .withDirectory("src/main/java/")));

        assertEquals(configuration.getGenerator().getGenerate().getJpaVersion(), JpaVersion.V_2_2);
    }
}