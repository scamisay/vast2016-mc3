package ar.edu.itba.service;

import ar.edu.itba.domain.Chunk;
import ar.edu.itba.domain.Data;
import ar.edu.itba.repository.ChunkRepository;
import ar.edu.itba.repository.DataRepository;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by scamisay on 26/06/16.
 */
@Service
public class DataService {

    @Autowired
    private ChunkRepository chunkRepository;

    @Autowired
    private DataRepository dataRepository;


    public void save(Chunk aChunk){
        chunkRepository.save(aChunk);
        List<Data> dataList = Data.buildDatas(aChunk);
        dataRepository.save(dataList);
    }
}
