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
    private static final String RETENTION_POLICY = "default";
    @ConfigProperty(name = "influx.url")
    String influxUrl;

    @ConfigProperty(name = "influx.username")
    String influxUsername;

    @ConfigProperty(name = "influx.password")
    String influxPassword;
    
    @ApplicationScoped
    public InfluxDB influxDbBean() {
        return InfluxDBFactory.connect(influxUrl, influxUsername, influxPassword).setRetentionPolicy(RETENTION_POLICY);
    }
}
