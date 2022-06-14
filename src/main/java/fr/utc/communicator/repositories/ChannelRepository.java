package fr.utc.communicator.repositories;

import fr.utc.communicator.models.Channel;
import fr.utc.communicator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.ManyToOne;
import java.util.List;


public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findByOwner(User owner);
}
