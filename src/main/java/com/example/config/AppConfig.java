package com.example.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

/**
 * Loads configuration from environment variables or config files.
 * Usage : ConfigManager.get().baseUrl()
 *
 * Priority : system property > env variable > config file
 */
@Config.Sources({
    "system:properties",
    "system:env",
    "classpath:config/${env}.properties",
    "classpath:config/default.properties"
})
public interface AppConfig extends Config {

    @Key("BASE_URL")
    @DefaultValue("https://www.saucedemo.com")
    String baseUrl();

    @Key("BROWSER")
    @DefaultValue("chrome")
    String browser();

    @Key("HEADLESS")
    @DefaultValue("false")
    boolean headless();

    @Key("IMPLICIT_WAIT")
    @DefaultValue("10")
    int implicitWait();

    @Key("EXPLICIT_WAIT")
    @DefaultValue("15")
    int explicitWait();

    @Key("ENV")
    @DefaultValue("default")
    String env();

    static AppConfig get() {
        return ConfigFactory.create(AppConfig.class);
    }
}
