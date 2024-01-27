package com.techrecipes.file.text.Filters;

import com.techrecipes.file.text.Filter;
import com.techrecipes.file.text.TemplateJson;

import java.util.List;
import java.util.stream.Collectors;

public class LastFilter implements Filter {
    @Override
    public List<TemplateJson> filter(List<TemplateJson> template) {
        // Filter out elements that are not hotels, hostels, or motels
        return template.stream()
                .filter(tem -> {
                    String tourismTag = tem.getTags().get("tourism");
                    return !"information".equals(tourismTag) &&
                            !"guest_house".equals(tourismTag) &&
                            !"picnic_site".equals(tourismTag) &&
                            !"camp-site".equals(tourismTag)&&
                             !"apartment".equals(tourismTag);
                })
                .collect(Collectors.toList());
    }
}
