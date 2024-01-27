package com.techrecipes.file.text.Filters;

import com.techrecipes.file.text.Filter;
import com.techrecipes.file.text.TemplateJson;

import java.util.List;
import java.util.stream.Collectors;

public class IsNotAttraction implements Filter {
    @Override
    public List<TemplateJson> filter(List<TemplateJson> template) {
        // Filter out elements that are not "attraction"
        return template.stream()
                .filter(tem -> !"attraction".equals(tem.getTags().get("tourism")))
                .collect(Collectors.toList());
    }
}
