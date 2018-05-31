package com.yuriy.labs.springcloud.microservices.common;

public class EventDto {
    private Integer id;
    private String payload;
    private String type;

    public EventDto() {}

    public EventDto(Integer id, String payload, String type) {
        this.id = id;
        this.payload = payload;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventDto eventDto = (EventDto) o;

        if (id != null ? !id.equals(eventDto.id) : eventDto.id != null) return false;
        if (payload != null ? !payload.equals(eventDto.payload) : eventDto.payload != null) return false;
        return type != null ? type.equals(eventDto.type) : eventDto.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", payload='" + payload + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
