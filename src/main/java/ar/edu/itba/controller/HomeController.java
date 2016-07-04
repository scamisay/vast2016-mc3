package ar.edu.itba.controller;

import ar.edu.itba.domain.Point;
import ar.edu.itba.domain.WSClient;
import ar.edu.itba.repository.DataRepository;
import ar.edu.itba.service.DataService;
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
    private DataService dataService;

    @RequestMapping("/")
    public ModelAndView home() {

        List<String> variables = dataService.findAllVariables();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("variables", variables);
        return mav;
    }

    @RequestMapping(value="/process", method = RequestMethod.POST)
    @ResponseBody
    public String process(@ModelAttribute(value = "variable") String variable) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Point> points = dataService.findDataByVariable(variable);
        return mapper.writeValueAsString(points);
    }

}
