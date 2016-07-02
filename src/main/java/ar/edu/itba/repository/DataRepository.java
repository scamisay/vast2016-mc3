package ar.edu.itba.repository;

import ar.edu.itba.domain.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by scamisay on 26/06/16.
 */
public interface DataRepository extends MongoRepository<Data, String> {

    public List<Data> findByKey(String key);
}
