package fr.utc.communicator.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void SetId(Long c) {id = c;}
    public Long GetId() {return id ;}

    @Column(name = "sender_id")
    Long sender;
    public void SetSender(Long s) {sender = s;}
    public Long GetSender() {return sender;}

    @Column(name = "receiver_id")
    private Long receiver;
    public Long GetReceiver() { return receiver;}
    public void SetReceiver(Long r) {receiver = r;}

    @Column()
    String contents;
    public String GetContents() { return contents;}
    public void SetContents(String c) {contents = c;}

}
