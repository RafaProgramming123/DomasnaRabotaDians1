package com.techrecipes.file.text.Filters;

import com.techrecipes.file.text.Filter;
import com.techrecipes.file.text.TemplateJson;

import java.util.List;
import java.util.stream.Collectors;

public class IsNotViewPoint implements Filter {
    @Override
    public List<TemplateJson> filter(List<TemplateJson> template) {
        // Filter out elements that are not view points
        return template.stream()
                .filter(tem -> !"viewpoint".equals(tem.getTags().get("tourism")))
                .collect(Collectors.toList());
    }
}
