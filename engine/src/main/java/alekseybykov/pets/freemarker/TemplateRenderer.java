package alekseybykov.pets.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class TemplateRenderer {

    private final Configuration configuration;

    public TemplateRenderer() {
        configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public String render(String templateString, Map<String, Object> model) throws Exception {
        Template template = new Template(
                "dynamicTemplate",
                new StringReader(templateString),
                configuration
        );
        StringWriter writer = new StringWriter();
        template.process(model, writer);
        return writer.toString();
    }
}
