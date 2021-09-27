package com.learn.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"));
        return getFilterMappingJacksonValue(someBeans, new String[]{"field1", "field2"});
    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value12", "value13"));
        return  getFilterMappingJacksonValue(someBeans, new String[]{"field2", "field3"});
    }

    public MappingJacksonValue getFilterMappingJacksonValue(List<SomeBean> someBeans, String[] fields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        mapping.setFilters(filters);
        return mapping;
    }
}