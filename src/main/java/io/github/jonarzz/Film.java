package io.github.jonarzz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Film extends Entity {

    @Builder
    public Film(long id, String name, @NonNull String description, @NonNull LocalDate releaseDate,
                @NonNull int duration, List<Genre> genres, Rating mpa,
                Set<Long> likes) {
        super(id);
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genres = genres;
        this.mpa = mpa;
        this.likes = likes;
    }

    @NotBlank
    private final String name;
    @NonNull
    @Size(max = 200, message = "Description name longer than 200 symbols")
    private final String description;
    @NonNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate releaseDate;

    @NonNull
    @Positive
    private int duration;

    private Rating mpa;

    private List<Genre> genres;

    @Setter(value = AccessLevel.PRIVATE)
    private Set<Long> likes;

}
