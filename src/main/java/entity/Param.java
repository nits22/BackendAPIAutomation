package entity;



public class Param {
    private String value;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    public Param(String key, String value){
        this.key = key;
        this.value = value;
    }


}
