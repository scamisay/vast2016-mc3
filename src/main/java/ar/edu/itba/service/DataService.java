package ar.edu.itba.service;

import ar.edu.itba.domain.Chunk;
import ar.edu.itba.domain.Data;
import ar.edu.itba.domain.Interval;
import ar.edu.itba.domain.Point;
import ar.edu.itba.repository.ChunkRepository;
import ar.edu.itba.repository.DataRepository;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<String> findAllVariables(){
        return dataRepository.findAll().stream()
                .map(d -> d.getKey())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Point> findDataByVariable(String variable) {
        return dataRepository.findByKey(variable).stream()
                .map(d -> new Point(d.getReportedDate(),d.getValue()))
                .collect(Collectors.toList());
    }

    private Date firstDateByVariable(String variable){
        return dataRepository.findByKey(variable).stream()
                .map(d -> d.getReportedDate())
                .findFirst().get();
    }

    public List<Point> findDataByVariable(String variable, Date fromDate, Date toDate) {
        Stream<Data> stream = dataRepository.findByKey(variable).stream();
        if(fromDate != null){
            stream = stream.filter(d -> fromDate.before(d.getReportedDate()));
        }
        if(toDate != null){
            stream = stream.filter(d -> d.getReportedDate().before(toDate));
        }
        return stream.map(d -> new Point(d.getReportedDate(),d.getValue()))
        .collect(Collectors.toList());
    }
}
