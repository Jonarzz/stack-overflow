package io.github.jonarzz;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class Answer implements Replacer {

    // we can simply add new contact types and their matchers using the constant below
    private static final Map<Pattern, ContactType> CONTACT_PATTERNS = Map.of(
            Pattern.compile("\\[(\\S+)]"), ContactType.TEL,
            Pattern.compile("\\{(\\S+)}"), ContactType.MAIL
    );

    @Override
    public String replace(String input, String[] dictionary) {
        // we're mapping the dictionary to make it easier to use and more readable (also in debugging)
        Map<ContactType, Map<String, String>> contactTypeToNameToValue =
                Arrays.stream(dictionary)
                      .map(entry -> entry.split(" ")) // dictionary entry is split by ' ' character
                      .collect(groupingBy(entry -> ContactType.fromString(entry[0]), // first split part is the contact type
                                          toMap(entry -> entry[1], // second part is the person's name
                                                entry -> entry[2]))); // third part is the contact value
        String output = input;
        for (Map.Entry<Pattern, ContactType> entry : CONTACT_PATTERNS.entrySet()) {
            Pattern pattern = entry.getKey();
            ContactType contactType = entry.getValue();
            output = pattern.matcher(output)
                            .replaceAll(matchResult -> {
                                String name = matchResult.group(1);
                                // we search our dictionary and get value from it or get the original value if nothing matches given name
                                return Optional.ofNullable(contactTypeToNameToValue.get(contactType))
                                               .map(nameToValue -> nameToValue.get(name))
                                               .orElseGet(matchResult::group);
                            });
        }
        return output;
    }

    public enum ContactType {
        TEL,
        MAIL;

        private static ContactType fromString(String value) {
            return Arrays.stream(values())
                         .filter(enumValue -> enumValue.name().equalsIgnoreCase(value))
                         .findFirst()
                         .orElseThrow(RuntimeException::new);
        }
    }

}
