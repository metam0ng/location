package com.location.api.server.testsupport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.location.api.server.config.DataFormatConfig;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ApiMockMvcFactory {

    private static final ObjectMapper objectMapper;
    private static final MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
    private static final FormattingConversionService conversionService;

    static {
        DataFormatConfig dataFormatConfig = new DataFormatConfig();
        jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(dataFormatConfig.jackson2ObjectMapperBuilder().build());
        objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        conversionService = new FormattingConversionService();
        dataFormatConfig.addFormatters(conversionService);
    }


    public static MockMvc getRestdocsMockMvc(RestDocumentationContextProvider restDocumentation, Object... controllers) {
        MockMvcRestDocumentationConfigurer documentationConfigurer = documentationConfiguration(restDocumentation);
        documentationConfigurer.uris().withScheme("http").withHost("location.api").withPort(443);
        return getMockMvcBuilder(controllers).apply(documentationConfigurer).build();
    }

    public static MockMvc getMockMvc(Object... controllers) {
        return getMockMvcBuilder(controllers).build();
    }

    public static StandaloneMockMvcBuilder getMockMvcBuilder(Object... controllers) {

        return MockMvcBuilders.standaloneSetup(controllers)
                .setConversionService(conversionService)
                .setMessageConverters(jackson2HttpMessageConverter)
                .alwaysDo(print());
    }

    public static String convertAsJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("json convert error.", e);
        }
    }
}
