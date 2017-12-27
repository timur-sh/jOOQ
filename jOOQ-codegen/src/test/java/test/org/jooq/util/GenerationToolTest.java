package test.org.jooq.util;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.junit.jupiter.api.Test;
import test.org.jooq.util.helpers.ConfigurationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GenerationToolTest {
    public static final GenerationToolTest OBJ = new GenerationToolTest();

    private void generatorUseManual() throws Exception {
        Configuration configuration = ConfigurationUtils.version3_11();
        configuration.getGenerator().getGenerate().withJpaAnnotations(true);
        GenerationTool.generate(configuration);
    }

    private void generatorUseXml() throws Exception {
        FileInputStream stream = new FileInputStream(new File("jOOQ-codegen/src/test/resources/configs/config.xml"));
        Configuration configuration = GenerationTool.load(stream);
        GenerationTool.generate(configuration);
    }

    public static void main(String args[]) throws Exception {
        OBJ.generatorUseXml();
    }

    @Test
    public void load() throws FileNotFoundException {
    }
}