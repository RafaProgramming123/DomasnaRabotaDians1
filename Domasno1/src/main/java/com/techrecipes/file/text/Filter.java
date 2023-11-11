package com.techrecipes.file.text;

import java.util.List;

public interface Filter {
    List<TemplateJson> filter(List<TemplateJson> template);
}
