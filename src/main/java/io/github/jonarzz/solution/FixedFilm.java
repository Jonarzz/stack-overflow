package io.github.jonarzz.solution;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.jonarzz.Entity;
import io.github.jonarzz.Genre;
import io.github.jonarzz.Rating;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FixedFilm extends Entity {

    @Builder
    @ConstructorProperties({"id", "name", "description", "releaseDate", "duration", "genres", "mpa", "likes"})
    public FixedFilm(long id, String name, @NonNull String description, @NonNull LocalDate releaseDate,
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
    private String name;
    @NonNull
    @Size(max = 200, message = "Description name longer than 200 symbols")
    private String description;
    @NonNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate releaseDate;

    @NonNull
    @Positive
    private int duration;

    private Rating mpa;

    private List<Genre> genres;

    @Setter(value = PRIVATE)
    private Set<Long> likes;

}
