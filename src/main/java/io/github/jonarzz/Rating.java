package io.github.jonarzz;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class Rating {

    @Positive
    private final long id;

}