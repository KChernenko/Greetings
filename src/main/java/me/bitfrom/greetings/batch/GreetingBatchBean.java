package me.bitfrom.greetings.batch;

import me.bitfrom.greetings.model.Greeting;
import me.bitfrom.greetings.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GreetingBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GreetingService greetingService;

    @Autowired
    public GreetingBatchBean(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    //@Scheduled(cron = "0,30 * * * * *")
    public void cronJob() {
        logger.info("> cronJob");

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.", greetings.size());

        logger.info("< cronJob");
    }

    //@Scheduled(initialDelay = 5000, fixedRate = 15000)
//    public void fixedRateJobWithInitialDelay() {
//        logger.info("> fixedRateJobWithInitialDelay");
//
//        //Add scheduled logic here
//        //Simulate job processing time
//        long pause = 5000;
//        long start = System.currentTimeMillis();
//        do {
//            if (start + pause < System.currentTimeMillis()) {
//                break;
//            }
//        } while (true);
//
//        logger.info("Processing time was {} seconds.", pause / 1000);
//
//        logger.info("< fixedRateJobWithInitialDelay");
//    }

    @Scheduled(initialDelay = 5000, fixedDelay = 150000)
    public void fixedDelayJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        //Add scheduled logic here
        //Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);

        logger.info("Processing time was {} seconds.", pause / 1000);

        logger.info("< fixedRateJobWithInitialDelay");
    }
}