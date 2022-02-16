package fr.miage.m1.sntp.cli;

import java.util.Collections;
import java.util.Map;

/**
 * @author Quentin Vaillant
 */
public class RunnerData {

    private final String initData;
    private Map<String, String> sessionData = Collections.emptyMap();

    public RunnerData(String initData) {
        this.initData = initData;
    }

    public String getInitData() {
        return initData;
    }

    public Map<String, String> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Map<String, String> sessionData) {
        this.sessionData = sessionData;
    }
}
