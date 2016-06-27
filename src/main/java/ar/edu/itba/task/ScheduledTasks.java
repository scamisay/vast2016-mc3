package ar.edu.itba.task;

import ar.edu.itba.domain.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scamisay on 26/06/16.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private WSClient wsClient;

    private boolean isConsumming = false;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        if(!isConsumming){
            isConsumming = true;
            wsClient.startConsuming();
        }
    }
}