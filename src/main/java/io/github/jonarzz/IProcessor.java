package io.github.jonarzz;

interface IProcessor {

    default String process(Object input) {
        return "totally not a test!";
    }

}
