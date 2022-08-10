package io.github.jonarzz;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class Genre {

    @Positive
    private final long id;

}
