package fr.crooser.hypervisor.natives.config;

public class ConfigObject {

    private final String key;
    private Object value;
    private boolean optional;

    public ConfigObject(String key, Object value, boolean optional) {
        this.key = key;
        this.value = value;
        this.optional = optional;
    }

    public String getKey() {

        return key;
    }

    public Object getValue() {

        return value;
    }
    public void setValue(Object value) {

        this.value = value;
    }

    public boolean isOptional() {

        return optional;
    }
    public void setOptional(boolean optional) {

        this.optional = optional;
    }
}
