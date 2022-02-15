package models;

public class ServerLog {
    private long timestamp;
    private String id;
    private String state;
    private String host;
    private String type;

    public long getTimeStamp() {
        return this.timestamp;
    }

    public String getId() {
        return this.id;
    }

    public String getHost() {
        return this.host;
    }

    public String getType() {
        return this.type;
    }

    public String getState() {
        return this.state;
    }

    public static class Builder {
        private final String id;
        private final long timestamp;
        private final String state;
        private String host;
        private String type;

        public Builder(String id, long timestamp, String state) {
            this.id = id;
            this.timestamp = timestamp;
            this.state = state;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public ServerLog build() {
            ServerLog serverLog = new ServerLog();
            serverLog.id = this.id;
            serverLog.timestamp = this.timestamp;
            serverLog.type = this.type;
            serverLog.host = this.host;
            serverLog.state = this.state;
            return serverLog;
        }
    }
}
