package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestClass {

    private String input = "In NY you can contact peter via telephone number [Peter] or e-mail {Peter}. " +
                           "In London you can contact anna via telephone number [Anna] or e-mail {Anna}. "
                           + "In Chicago you can contact shawn via telephone number [Shawn] or e-mail {Shawn}";
    private String[] dictionary = { "Mail Peter peter@gmail.com", "Tel Anna +3456", "Tel Shawn +1234", "Mail Shawn shawn@yahoo.com" };

    @Test
    void questionTest() {
        String expectedOutput = "In NY you can contact peter via telephone number [peter@gmail.com] or e-mail {peter@gmail.com}. " +
                                "In London you can contact anna via telephone number [+3456] or e-mail {Anna}. In Chicago you can " +
                                "contact shawn via telephone number [+1234] or e-mail {shawn@yahoo.com}";

        String output = new Question().replace(input, dictionary);

        assertEquals(expectedOutput, output);
    }

    @Test
    void answerTest() {
        String expectedOutput = "In NY you can contact peter via telephone number [Peter] or e-mail peter@gmail.com. " +
                                "In London you can contact anna via telephone number +3456 or e-mail {Anna}. In Chicago you can " +
                                "contact shawn via telephone number +1234 or e-mail shawn@yahoo.com";

        String output = new Answer().replace(input, dictionary);

        assertEquals(expectedOutput, output);
    }

}