package fr.utc.communicator.repositories;

import fr.utc.communicator.models.Message;
import fr.utc.communicator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ManyToOne;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> { }
