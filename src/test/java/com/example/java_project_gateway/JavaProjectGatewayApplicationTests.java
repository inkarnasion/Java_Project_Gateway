package com.example.java_project_gateway;

import com.example.java_project_gateway.dto.xml.XmlExchangeRatesCommand;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class JavaProjectGatewayApplicationTests {

//    @Test
//    void contextLoads() {
//    }

	@Test
	public void testDesirializeCurrentExchangeRatesCommand() throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		XmlExchangeRatesCommand value
				= xmlMapper.readValue(
				"<command id=\"1234\" >\n" +
						"  <get consumer=\"1652024044\" >\n" +
						"    <currency>EUR</currency>\n" +
						"  </get>\n" +
						"</command>",
				XmlExchangeRatesCommand.class);

		System.out.println(value);
	}

	@Test
	public void testDesirializeExchangeRatesHistoryCommand() throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		XmlExchangeRatesCommand value
				= xmlMapper.readValue(
				"<command id=\"1234-8785\" >\n" +
						"  <history consumer=\"1652024044\" currency=\"EUR\" period=\"24\" />\n" +
						"</command>\n",
				XmlExchangeRatesCommand.class);

		System.out.println(value);
	}
}
