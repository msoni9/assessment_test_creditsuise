package models;

public class Event {
    private String id;
    private long duration;
    private boolean alert;
    private String type;
    private String host;


    public String getId() {
        return this.id;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getType() {
        return this.type;
    }

    public String getHost() {
        return this.host;
    }

    public boolean isAlert() {
        return this.alert;
    }

    public static class Builder {
        private final String id;
        private final long duration;
        private final boolean alert;
        private String type;
        private String host;

        public Builder(String id, long duration, boolean alert) {
            this.id = id;
            this.duration = duration;
            this.alert = alert;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.alert = this.alert;
            event.duration = this.duration;
            event.type = this.type;
            event.host = this.host;
            event.id = this.id;
            return event;
        }
    }
}
