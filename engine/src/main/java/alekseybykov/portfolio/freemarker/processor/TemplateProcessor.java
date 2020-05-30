package alekseybykov.portfolio.freemarker.processor;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @author Aleksey Bykov
 * @since 16.06.2019
 */
public interface TemplateProcessor {
    String process(String templateName, Map model) throws IOException, TemplateException;
}
