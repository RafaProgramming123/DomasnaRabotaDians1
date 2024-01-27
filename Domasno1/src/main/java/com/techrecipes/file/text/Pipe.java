package com.techrecipes.file.text;

import java.util.List;

public class Pipe {
    private List<Filter> filters;

    public Pipe(List<Filter> filters) {
        this.filters = filters;
    }

    public List<TemplateJson> process(List<TemplateJson> template) {
        for (Filter filter : filters) {
            template = filter.filter(template);
        }
        return template;
    }
}

