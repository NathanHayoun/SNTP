package libs;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;
import io.vertx.mqtt.messages.MqttConnAckMessage;
import io.vertx.mqtt.messages.MqttMessage;

public class LibMQTT {
    private static final boolean MQTT_CLEAN_SESSION = true;
    private static final boolean MQTT_AUTO_KEEP_ALIVE = true;
    private MqttClient mqttClient;
    private String broker;
    private String clientID;
    private String username;
    private String password;
    private int port;
    private Vertx vertx;

    public LibMQTT(Vertx vertx, String broker, String clientID, String username, String password, int port) {
        this.vertx = vertx;
        this.broker = broker;
        this.clientID = clientID;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    // init connection
    public Future<Void> init() {
        Promise<Void> promise = Promise.promise();
        Future<MqttConnAckMessage> future;

        // init mqtt options
        MqttClientOptions mqttClientOptions = new MqttClientOptions();
        mqttClientOptions.setCleanSession(MQTT_CLEAN_SESSION);
        mqttClientOptions.setClientId(clientID);
        mqttClientOptions.setUsername(username);
        mqttClientOptions.setPassword(password);
        mqttClientOptions.setAutoKeepAlive(MQTT_AUTO_KEEP_ALIVE);
        mqttClient = MqttClient.create(vertx, mqttClientOptions);
        future = mqttClient.connect(port, broker);
        future.onComplete(res -> {
            if (res.succeeded()) {
                System.out.println("Connected to MQTT broker");
                promise.complete();
            } else {
                System.out.println("Failed to connect to MQTT broker");
                promise.fail(res.cause());
            }
        });

        return promise.future();
    }

    public Future<Boolean> publish(String topic, JsonObject payload) {
        Promise<Boolean> p = Promise.promise();
        Future<Integer> future;
        future = mqttClient.publish(topic, payload.toBuffer(), MqttQoS.AT_MOST_ONCE, false, false);
        future.onComplete(res -> {
            if (res.succeeded()) {
                System.out.println("Published to MQTT topic");
                p.complete(true);
            } else {
                System.out.println("Failed to publish to MQTT topic");
                p.fail(res.cause());
            }
        });

        return p.future();
    }

    public void subscribe(String topic, MqttMessage message) {
        mqttClient.subscribe(topic, 0, ar -> {
            if (ar.succeeded()) {
                System.out.println("Subscribed to MQTT topic");
            } else {
                System.out.println("Failed to subscribe to MQTT topic");
            }
        });
    }

    public void unsubscribe(String topic) {
        mqttClient.unsubscribe(topic);
    }

    // MQTT message callback
    public interface MQTTMessageCallback {
        void onMessage(String topic, String message);
    }

    // MQTT message callback
    private MQTTMessageCallback mqttMessageCallback;

    // Constructor
    public LibMQTT(MQTTMessageCallback mqttMessageCallback) {
        this.mqttMessageCallback = mqttMessageCallback;
    }

    // Subscribe to MQTT topic
    private void subscribe(String topic) {
        mqttClient.subscribe(topic, 0 , ar -> {
            if (ar.succeeded()) {
                System.out.println("Subscribed to MQTT topic");
            } else {
                System.out.println("Failed to subscribe to MQTT topic");
            }
        });
    }

    // Disconnect from MQTT broker
    public void disconnect() {
        mqttClient.disconnect(ar -> {
            if (ar.succeeded()) {
                System.out.println("Disconnected from MQTT broker");
            } else {
                System.out.println("Failed to disconnect from MQTT broker");
            }
        });
    }
}
