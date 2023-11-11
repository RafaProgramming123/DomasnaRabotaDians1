package com.techrecipes.file.text.Filters;

import com.techrecipes.file.text.Filter;
import com.techrecipes.file.text.TemplateJson;

import java.util.List;
import java.util.stream.Collectors;

public class HasTags implements Filter {
    @Override
    public List<TemplateJson> filter(List<TemplateJson> template) {
        // Filter museums that have a website
        return template.stream()
                .filter(tem -> tem.getTags() != null)
                .collect(Collectors.toList());
    }
}
