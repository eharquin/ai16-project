package fr.utc.communicator.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void SetId(Long c) {id = c;}
    public Long GetId() {return id ;}

    @Column
    String name;
    public void SetName(String n) {name = n;}
    public String GetName() {return name;}

    @Column
    private String password;
    public String GetPassword() { return password;}
    public void SetPassword(String p) {password = p;}

//    @ManyToMany(mappedBy="")
//    private List<User> users;
//    public List<User> GetUsers() { return users;}

    @OneToMany(mappedBy="receiver")
    private List<Message> messages;
    public List<Message> GetMessages() { return messages;}

    public Channel()
    {
    }
}
