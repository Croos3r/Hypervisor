package fr.crooser.hypervisor.natives.config;

public class ConfigObject {

    private final String path;
    private Object value;
    private boolean optional;
    private final boolean section;

    public ConfigObject(String path, Object value, boolean optional) {
        this.path = path;
        this.value = value;
        this.optional = optional;
        this.section = false;
    }
    public ConfigObject(String path, boolean optional) {

        this.path = path;
        this.optional = optional;
        this.value = null;
        this.section = true;
    }

    public String getPath() {

        return path;
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

    public boolean isSection() {

        return section;
    }
}
