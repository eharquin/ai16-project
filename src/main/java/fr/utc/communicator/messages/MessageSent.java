package fr.utc.communicator.messages;

public class MessageSent extends WSMessage{

    private String content;

    private String username;

    public MessageSent() {
    }

    public MessageSent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content){ this.content = content;}

    public String getUsername(){ return username;}
    public void setUsername(String username){ this.username = username;}
}

