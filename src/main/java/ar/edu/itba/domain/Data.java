package ar.edu.itba.domain;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by scamisay on 26/06/16.
 */
public class Data {

    private static final String TIME_NAME = "Date/Time";
    private static final String TYPE_NAME = "type";
    private static final List<String> SKYPPING_LIST =Arrays.asList(TIME_NAME, TYPE_NAME);

    @Id
    private String id;
    private String type;
    private Date reportedDate;
    private Date timestamp;
    private String key;
    private Double value;

    private Data(){}

    public Data(String type, Date reportedDate, Date timestamp, String key, Double value) {
        this.type = type;
        this.reportedDate = reportedDate;
        this.timestamp = timestamp;
        this.key = key;
        this.value = value;
    }

    public static List<Data> buildDatas(Chunk aChunk) {
        if (aChunk == null) {
            throw new RuntimeException("Argument cannot be null");
        }

        List<Data> list = new ArrayList<>();

        Date timestamp = aChunk.getTimestamp();

        //ahora lo parseo
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject)new JSONParser().parse(aChunk.getJson());

            String type = (String)jsonObject.get(TYPE_NAME);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date reportedDate = df.parse((String)jsonObject.get(TIME_NAME));

            List<String> valuesToRead =
                    (List<String>) jsonObject.keySet().stream()
                            .filter(e -> !SKYPPING_LIST.contains(e))
                            .collect(Collectors.toList());
            list = valuesToRead.stream()
                    .map( p ->
                                new Data(type, reportedDate, timestamp, p,
                                        Double.parseDouble(String.valueOf(jsonObject.get(p)))
                                )
                    )
                    .collect(Collectors.toList());

        } catch (Exception e) {}

        return list;

        /*
        db.data.aggregate([{$group: {_id: "$type", num_datas: {$sum : 1}}}])
        db.data.aggregate([{$group: {_id: "$key", num_histograms: {$sum : 1}}}])
        db.data.find({key: "F_1_Z_7: Equipment Power"})
        */
    }


    public String getType() {
        return type;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getKey() {
        return key;
    }

    public Double getValue() {
        return value;
    }
}
