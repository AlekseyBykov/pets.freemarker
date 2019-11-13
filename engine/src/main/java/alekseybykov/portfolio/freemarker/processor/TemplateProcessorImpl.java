package alekseybykov.portfolio.freemarker.processor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
public class TemplateProcessorImpl implements TemplateProcessor {

    @Override
    public String process(String templateName, Map model) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");

        Template template = configuration.getTemplate(templateName);

        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);

        return stringWriter.toString();
    }
}
