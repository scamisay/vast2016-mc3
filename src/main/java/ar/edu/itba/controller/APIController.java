package ar.edu.itba.controller;

import ar.edu.itba.domain.Point;
import ar.edu.itba.service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by scamisay on 03/07/16.
 */

@RestController("api")
public class APIController {

    @Autowired
    private DataService dataService;

    @RequestMapping("variables")
    public List<String> variables(){
        return dataService.findAllVariables();
    }

    @RequestMapping("values")
    public String process(@ModelAttribute(value = "variable") String variable,
                          @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd:HH") Date fromDate,
                          @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd:HH") Date toDate
                          ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Point> points = dataService.findDataByVariable(variable, fromDate, toDate);
        return mapper.writeValueAsString(points);
    }
    //http://localhost.com:8080/values?variable=F_1_Z_4:%20Thermostat%20Heating%20Setpoint&from=2016-05-30:10&to=2016-08-14:18

}
