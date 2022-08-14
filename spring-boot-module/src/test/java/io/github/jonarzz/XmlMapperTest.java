package io.github.jonarzz;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

public class XmlMapperTest {

    static final String XML = """
            <?xml version = '1.0' encoding = 'utf-8'?>
                            
            <dataModel xmlns="http://xmlns.oracle.com/oxp/xmlp" version="2.0" xmlns:xdm="http://xmlns.oracle.com/oxp/xmlp" xmlns:xsd="http://wwww.w3.org/2001/XMLSchema">
                 <group name="G_1" label="G_1" source="control_subject_params">
                      <element name="NAME1" value="VALUE1" label="1" dataType="xsd:string" breakOrder="" fieldOrder="1"/>
                      <element name="NAME2" value="VALUE2" label="2" dataType="xsd:string" breakOrder="" fieldOrder="2"/>
                </group>
                            
            </dataModel>
            """;

    @Test
    void test() throws JsonProcessingException {
        var xmlMapper = new XmlMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        var module = new SimpleModule("Module");
        module.addDeserializer(Group.class, new GroupDeserializer());
        xmlMapper.registerModule(module);

        var result = xmlMapper.readValue(XML, DataModel.class);

        assertThat(result.getGroup())
                .extracting(Group::getElements)
                .flatExtracting(Elements::getElements)
                .extracting(Element::getName, Element::getValue)
                .containsExactly(
                        tuple("NAME1", "VALUE1"),
                        tuple("NAME2", "VALUE2")
                );
    }
}
