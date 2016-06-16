package ar.edu.itba.domain;

import java.util.List;

/**
 * Created by scamisay on 15/06/16.
 */
public interface PersistentEntity {

    void saveMessage(String message);

    List<String> listMessages();

}
