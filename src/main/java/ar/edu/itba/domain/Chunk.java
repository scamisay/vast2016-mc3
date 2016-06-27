package ar.edu.itba.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by scamisay on 26/06/16.
 */
public class Chunk {

    @Id
    private String id;
    private Date timestamp;
    private String json;

    public Chunk(Date timestamp, String json) {
        this.timestamp = timestamp;
        this.json = json;
    }

    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getJson() {
        return json;
    }
}
