package alekseybykov.portfolio.freemarker.processor;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TemplateProcessorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TemplateProcessor templateProcessor;

    @Test
    public void testProcessTemplate01() throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("value", 1);

        String actual = templateProcessor.process("simple-expression01.ftl", model);

        assertEquals(actual, "0001");
        assertNotEquals(actual, "0000");
    }

    @Test
    public void testProcessTemplate02() throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("string", "test & test");

        String actual = templateProcessor.process("simple-expression02.ftl", model);

        assertEquals(actual, "test &amp; test");
        assertNotEquals(actual, "test & test");
    }

    @Test
    public void testProcessTemplate03() throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("boolean", true);

        String actual = templateProcessor.process("simple-expression03.ftl", model);

        assertEquals(actual, "+++");
        assertNotEquals(actual, "---");
    }
}
