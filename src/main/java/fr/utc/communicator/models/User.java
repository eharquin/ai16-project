package fr.utc.communicator.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String mail;
    @Column
    private boolean isAdmin;
    @Column
    private String password;
    @ManyToMany(mappedBy = "members")
    private List<Channel> channels;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfilePictureUrl() {
        return String.format("https://ui-avatars.com/api/?name=%s&background=212936&color=D2D5DA&size=128", getName());
    }

    public List<Channel> getChannels() {
        return channels;
    }
}