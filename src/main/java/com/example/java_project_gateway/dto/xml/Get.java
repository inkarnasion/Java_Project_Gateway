package com.example.java_project_gateway.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Get implements Serializable {

    @JacksonXmlProperty(isAttribute = true)
    private String consumer;

    @JacksonXmlProperty
    private String currency;
}
