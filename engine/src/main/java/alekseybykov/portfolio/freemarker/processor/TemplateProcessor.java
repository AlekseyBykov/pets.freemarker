//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.freemarker.processor;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-05-30
 */
public interface TemplateProcessor {
    String process(String templateName, Map model) throws IOException, TemplateException;
}
