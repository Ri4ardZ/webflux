package com.example.demo.entity;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cams {
    private Long id;
    private CamsType urlType;
    private String videoUrl;
    private String value;
    private String ttl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cams cams = (Cams) o;
        return id.equals(cams.id) &&
                urlType == cams.urlType &&
                videoUrl.equals(cams.videoUrl) &&
                value.equals(cams.value) &&
                ttl.equals(cams.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlType, videoUrl, value, ttl);
    }
}
