package ar.edu.itba.domain.impl;

import ar.edu.itba.domain.PersistentEntity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by scamisay on 15/06/16.
 */
public class FilePersistentEntity implements PersistentEntity {

    private String fileName;
    private Map<Date,String> map;

    public FilePersistentEntity(String fileName){
        this.fileName = fileName;
        this.map = new HashMap<>();
    }

    @Override
    public void saveMessage(String message) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(message);
            map.put(new Date(), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> listMessages() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
