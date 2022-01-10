package fr.miage.m1.sntp.configuration;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.net.UnknownHostException;

@ApplicationScoped
@Named("myNamedBean")
@RegisterForReflection
public class InfluxDBConfig {
    @ApplicationScoped
    public InfluxDB influxDbBean() throws UnknownHostException {
        //    @ConfigProperty(name = "influx.url")
        String influxUrl = "http://sntpinfluxdb.nathan-hayoun.fr:80";
        //    @ConfigProperty(name = "influx.username")
        String influxUsername = "sntp";
        //    @ConfigProperty(name = "influx.password")
        String influxPassword = "sntpPassword1234";
        return InfluxDBFactory.connect(influxUrl, influxUsername, influxPassword).setRetentionPolicy("default");
    }
}
