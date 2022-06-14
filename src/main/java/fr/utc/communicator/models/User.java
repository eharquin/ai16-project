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

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMail() {
        return mail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getProfilePictureUrl() {
        return String.format("https://ui-avatars.com/api/?name=%s&background=212936&color=D2D5DA&size=128", getName());
    }

    public List<Channel> getChannels() {
        return channels;
    }
}