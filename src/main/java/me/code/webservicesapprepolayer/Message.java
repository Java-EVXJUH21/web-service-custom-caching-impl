package me.code.webservicesapprepolayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "chatmessages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String content;

    @ManyToOne
    private User user;

    private Date date;

    public Message(String content, User user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }

}
