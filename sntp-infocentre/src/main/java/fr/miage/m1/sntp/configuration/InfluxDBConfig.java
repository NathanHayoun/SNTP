package fr.miage.m1.sntp.configuration;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("influxDBBean")
@RegisterForReflection
public class InfluxDBConfig {
    public static final String INFLUX_URL_KEY = "influx.url";
    public static final String INFLUX_USERNAME_KEY = "influx.username";
    public static final String INFLUX_PASSWORD_KEY = "influx.password";
    private static final String RETENTION_POLICY = "default";
    @ConfigProperty(name = INFLUX_URL_KEY)
    String influxUrl;

    @ConfigProperty(name = INFLUX_USERNAME_KEY)
    String influxUsername;

    @ConfigProperty(name = INFLUX_PASSWORD_KEY)
    String influxPassword;

    @ApplicationScoped
    public InfluxDB influxDbBean() {
        return InfluxDBFactory.connect(influxUrl, influxUsername, influxPassword).setRetentionPolicy(RETENTION_POLICY);
    }
}
