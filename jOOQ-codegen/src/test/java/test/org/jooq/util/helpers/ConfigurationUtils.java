package test.org.jooq.util.helpers;

import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generate;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.JpaVersion;
import org.jooq.util.jaxb.Strategy;
import org.jooq.util.jaxb.Target;

/**
 * @author Timur Shaidullin
 */
public class ConfigurationUtils {
    public static Configuration version3_10() {
        return new org.jooq.util.jaxb.Configuration()
           .withJdbc(new Jdbc()
              .withDriver("org.postgresql.Driver")
              .withUrl("jdbc:postgresql://localhost:50000/hibernate")
              .withUser("timur")
              .withPassword("1"))
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
                 .withFluentSetters(false))

              .withTarget(new Target()
                 .withPackageName("codegen")
                 .withDirectory("jOOQ-codegen/src/test/java/")));
    }

    public static Configuration version3_11() {
        Configuration configuration = version3_10();
        configuration.getGenerator()
           .getGenerate()
           .withJpaVersion(JpaVersion.V_2_2);
        return configuration;
    }
}
