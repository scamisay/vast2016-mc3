package ar.edu.itba.repository;

import ar.edu.itba.domain.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by scamisay on 26/06/16.
 */
public interface DataRepository extends MongoRepository<Data, String> {
}
