package com.example.utils;

import com.example.config.AppConfig;

/**
 * Static accessor for configuration — avoids passing AppConfig everywhere.
 */
public class ConfigManager {
    private ConfigManager() {}

    public static AppConfig get() {
        return AppConfig.get();
    }
}
