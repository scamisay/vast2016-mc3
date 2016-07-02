package ar.edu.itba.controller;

import ar.edu.itba.domain.Point;
import ar.edu.itba.domain.WSClient;
import ar.edu.itba.repository.DataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by scamisay on 16/06/16.
 */
@Controller
public class HomeController {

    @Autowired
    private DataRepository dataRepository;

    @RequestMapping("/")
    public ModelAndView home() {

        List<String> variables = dataRepository.findAll().stream().map(d -> d.getKey()).distinct().collect(Collectors.toList());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("variables", variables);
        return mav;
    }
    @RequestMapping(value="/process", method = RequestMethod.POST)
    @ResponseBody
    public String process(@ModelAttribute(value = "variable") String variable) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Point> points = findDataByVariable(variable);
        return mapper.writeValueAsString(points);
    }

    private List<Point> findDataByVariable(String variable) {
         return dataRepository.findByKey(variable).stream()
                .map(d -> new Point(d.getReportedDate(),d.getValue()))
                .collect(Collectors.toList());
    }
}
