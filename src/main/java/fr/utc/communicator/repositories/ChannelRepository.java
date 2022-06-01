package fr.utc.communicator.repositories;

import fr.utc.communicator.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ManyToOne;
import java.util.List;


public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
