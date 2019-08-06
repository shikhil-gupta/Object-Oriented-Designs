package custom.logger;

public enum LogLevels {

    INFO(1), DEBUG(2), WARN(3), ERROR(4);

    private Integer value;

    private LogLevels(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return  value;
    }
    
}
