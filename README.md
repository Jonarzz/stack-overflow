# Question
https://stackoverflow.com/questions/63364411/java-string-problem-replacing-specific-part-of-a-string

# Answer
Handling of strings in a specified format is done best using [regular expressions][1]. You define a specified pattern and after you find a part matching your pattern, you can replace it or analyze further.

It's best to write your code to make it easily extensible. For example - if a new contact form is added (home address, fax, business phone number), it should be easy to handle it in the code. Your solution makes it harder to resolve such problems as a whole new `if` branch is required and it's easy to make a mistake, it also makes the code less readable.

When dealing with a kind of dictionary (like your input String array), it's worth using a `Map` as it makes the processing faster and the code more readable. When a constant values are present, it's worth to define them too - as constants or enum values. Also - Java allows for writing more functional and more readable, functional-style code instead of nested for-eaches - it's worth using those features (JDK8+).

Please, find the code snippet below and [a whole project with tests comparing your solution to mine on GitHub][2] - you can view it there or clone the repository and verify the code yourself:

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



  [1]: https://en.wikipedia.org/wiki/Regular_expression
  [2]: https://github.com/Jonarzz/stack-overflow/tree/63364411