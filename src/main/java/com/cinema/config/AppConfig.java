package com.cinema.config;

import com.cinema.util.TPSCounter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Log4j2
public class AppConfig {
    @Value("${appconfig.max.tps.film}")
    public long maxTpsFilm;

    @Value("${appconfig.max.tps.user}")
    public long maxTpsUser;

    @Value("${appconfig.max.tps.other}")
    public long maxTpsOther;

    public String filmTpsName = "film";
    public String userTpsName = "user";
    public String otherTpsName = "other";
    private static final ConcurrentHashMap<String, TPSCounter> tpsCounter = new ConcurrentHashMap<>();

    @PostConstruct
    private synchronized void loadAppConfig() {
        initTPS("client");
    }

    public synchronized void initTPS(String username) {
        TPSCounter counter = new TPSCounter();
        tpsCounter.put(username, counter);
    }

    public int checkTps(String username) {
//        long startTime = System.currentTimeMillis();
//        TPSCounter counter = tpsCounter.get(username);
//
//        int tps = 1;
//        if (counter == null) {
//            counter = new TPSCounter();
//            tpsCounter.put(username, counter);
//        } else {
//            //Check time
//            long currentTime = System.currentTimeMillis();
//            long duration = (currentTime - counter.getCountTime());
//
//            if (duration > 1000) {
//                //reset tps count
//                counter.setCurrentTps(1);
//                counter.setCountTime(currentTime);
//            } else {
//                //Increase and return
//                tps = counter.increaseTps();
//            }
//        }
//
//        log.info("CURRENT_TPS: " + username + "|tps = " + tps);
//        return tps;


        TPSCounter counter = tpsCounter.get(username);
        int tps = 1;
        if (counter == null) {
            counter = new TPSCounter();
            tpsCounter.put(username, counter);
        }

        tps = counter.increaseTps();
        log.info("CURRENT_TPS: " + username + "|tps = " + tps);
        return tps;
    }

    public void decreaseTPS(String username) {
        TPSCounter counter = tpsCounter.get(username);
        if (counter == null) {
            counter = new TPSCounter();
            counter.setCurrentTps(0);
            tpsCounter.put(username, counter);
        } else {
            counter.decreaseTps();
        }
    }

}
