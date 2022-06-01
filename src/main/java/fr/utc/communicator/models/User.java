package fr.utc.communicator.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User  {
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
    String mail;
    public void SetMail(String m) {mail = m;}
    public String GetMail() {return mail;}

    @Column
    private boolean isAdmin;
    public boolean GetIsAdmin() {return isAdmin;}
    public void GetIsAdmin(boolean a) {isAdmin = a;}

    @Column
    private String password;
    public String GetPassword() { return password;}
    public void SetPassword(String p) {password = p;}

    @ManyToMany
    List<Channel> channnels;

    public User()
    {
    }
}