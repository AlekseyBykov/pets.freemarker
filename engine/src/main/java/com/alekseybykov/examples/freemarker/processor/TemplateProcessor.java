package com.alekseybykov.examples.freemarker.processor;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface TemplateProcessor {
    String process(String templateName, Map model) throws IOException, TemplateException;
}