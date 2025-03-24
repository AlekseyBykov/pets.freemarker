package alekseybykov.pets.freemarker;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateRendererTest {

    private final TemplateRenderer renderer = new TemplateRenderer();

    @Nested
    class SimpleExpressions {
        @Test
        void testSimpleVariable() throws Exception {
            String template = "Hello, ${user}!";
            Map<String, Object> model = Map.of("user", "User");

            String result = renderer.render(template, model);

            assertEquals("Hello, User!", result);
        }

        @Test
        void testMissingVariableWithDefault() throws Exception {
            String template = "Hello, ${user!\"Guest\"}!";
            Map<String, Object> model = Map.of();

            String result = renderer.render(template, model);

            assertEquals("Hello, Guest!", result);
        }
    }

    @Nested
    class Conditions {
        @Test
        void testIfElse() throws Exception {
            String template = "<#if isAdmin>Admin<#else>User</#if>";
            Map<String, Object> model = Map.of("isAdmin", true);

            String result = renderer.render(template, model);

            assertEquals("Admin", result);
        }

        @Test
        void testSwitchCase() throws Exception {
            String template = "<#switch role>" +
                              "<#case 'admin'>Admin<#break>" +
                              "<#case 'user'>User<#break>" +
                              "<#default>Guest" +
                              "</#switch>";
            Map<String, Object> model = Map.of("role", "user");

            String result = renderer.render(template, model);

            assertEquals("User", result);
        }
    }

    @Nested
    class Iterations {
        @Test
        void testListIteration() throws Exception {
            String template = "<#list items as item>${item} </#list>";
            Map<String, Object> model = Map.of("items", java.util.List.of("A", "B", "C"));

            String result = renderer.render(template, model);

            assertEquals("A B C ", result);
        }
    }

    @Nested
    class StringFunctions {
        @Test
        void testUpperCase() throws Exception {
            String template = "${text?upper_case}";
            Map<String, Object> model = Map.of("text", "hello");

            String result = renderer.render(template, model);

            assertEquals("HELLO", result);
        }

        @Test
        void testCapitalize() throws Exception {
            String template = "${text?capitalize}";
            Map<String, Object> model = Map.of("text", "hello world");

            String result = renderer.render(template, model);

            assertEquals("Hello World", result);
        }

        @Test
        void testReplace() throws Exception {
            String template = "${text?replace('dog', 'cat')}";
            Map<String, Object> model = Map.of("text", "The dog jumps over the dog.");

            String result = renderer.render(template, model);

            assertEquals("The cat jumps over the cat.", result);
        }
    }

    @Nested
    class MacrosAndIncludes {
        @Test
        void testSimpleMacro() throws Exception {
            String template = "<#macro greet name>Hi, ${name}! </#macro><@greet name='User'/>";
            Map<String, Object> model = Map.of();

            String result = renderer.render(template, model);

            assertEquals("Hi, User! ", result);
        }
    }

    @Nested
    class DateAndNumberFormatting {
        @Test
        void testDateFormatting() throws Exception {
            String template = "Today is ${today?string(\"yyyy-MM-dd\")}";
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2025-03-24");
            Map<String, Object> model = Map.of("today", date);

            String result = renderer.render(template, model);

            assertEquals("Today is 2025-03-24", result);
        }

        @Test
        void testNumberFormatting() throws Exception {
            String template = "Formatted: ${value?string(\"0.00\")}";
            Map<String, Object> model = Map.of("value", 1234.5678);

            String result = renderer.render(template, model);

            assertEquals("Formatted: 1234.57", result);
        }

        @Test
        void testNumberAsCurrency() throws Exception {
            String template = "${amount?string.currency}";
            Map<String, Object> model = Map.of("amount", 99.99);

            String result = renderer.render(template, model);

            boolean isValid = result.contains("99.99") || result.contains("99,99");
            assertTrue(isValid);
        }
    }

    @Nested
    class Escaping {
        @Test
        void testHtmlEscape() throws Exception {
            String template = "${unsafe?html}";
            Map<String, Object> model = Map.of("unsafe", "<div>Hello</div>");

            String result = renderer.render(template, model);

            assertEquals("&lt;div&gt;Hello&lt;/div&gt;", result);
        }
    }
}
