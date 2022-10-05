package io.github.jonarzz;

import javax.persistence.*;

@Entity
public class MyEntity {

    private Long id;

    void setId(Long id) {
        this.id = id;
    }

    @Id
    Long getId() {
        return id;
    }
}
