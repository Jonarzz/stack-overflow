package io.github.jonarzz;

import org.springframework.data.jpa.repository.*;

public interface MyRepository extends JpaRepository<MyEntity, Long> {

}
