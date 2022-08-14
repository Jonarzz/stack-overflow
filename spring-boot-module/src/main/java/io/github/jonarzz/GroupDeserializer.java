package io.github.jonarzz;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;

class GroupDeserializer extends StdDeserializer<Group> {

    private static final TypeReference<List<Element>> ELEMENT_LIST_TYPEREF = new TypeReference<>() {};

    GroupDeserializer() {
        super(Group.class);
    }

    @Override
    public Group deserialize(JsonParser parser, DeserializationContext ctx) throws IOException, JacksonException {
        var elements = new Elements();
        var elementNodes = parser.readValueAsTree()
                                 .get("element");
        try (var traverse = elementNodes.traverse(parser.getCodec())) {
            List<Element> elementsList = traverse.readValueAs(ELEMENT_LIST_TYPEREF);
            elements.setElements(elementsList);
        }
        var group = new Group();
        group.setElements(elements);
        return group;
    }
}
