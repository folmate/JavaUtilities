package io.folmate.javautilities.timeout;

import java.util.Timer;
import java.util.TimerTask;

public class Timeout {
    public static Timer timeout(long seconds, Callback callback){
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callback.call();
            }}, seconds*1000);
        return timer;
    }
}