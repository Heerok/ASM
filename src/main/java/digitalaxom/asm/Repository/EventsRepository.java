package digitalaxom.asm.Repository;

import digitalaxom.asm.db.Events;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Heerok on 04-10-2016.
 */
public interface EventsRepository extends JpaRepository<Events,Long> {

}
