package me.luckymutt.rw.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final Map<String, BaseConfig> configMap;

    public ConfigManager() {
        this.configMap = new HashMap<>();

        configMap.put("main", new BaseConfig("config.yml")
                .copy("config/config.yml")
                .load()
        );

        configMap.put("message", new BaseConfig("message.yml")
                .copy("config/message.yml")
                .load()
        );
    }

    public BaseConfig getConfig(String name) {
        return configMap.get(name);
    }

}
