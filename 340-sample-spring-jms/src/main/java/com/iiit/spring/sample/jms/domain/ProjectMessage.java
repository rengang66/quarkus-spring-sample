package com.iiit.spring.sample.jms.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class ProjectMessage implements Serializable {

    private String text;
    private int sequence;
    private boolean secret;
    private LocalDateTime sent;

    public ProjectMessage() {
    }

    public ProjectMessage(String text,
                         int sequence,
                         boolean secret,
                         LocalDateTime sent
                         ) {
        this.text = text;
        this.sequence = sequence;
        this.secret = secret;
        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public int getSequence() {
        return sequence;
    }

    public boolean isSecret() {
        return secret;
    }

    public LocalDateTime getSent() {
        return sent;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "text='" + text + "'" +
                ", sequence=" + sequence +
                ", secret=" + secret +
                ", sent=" + sent +
                '}';
    }
}

