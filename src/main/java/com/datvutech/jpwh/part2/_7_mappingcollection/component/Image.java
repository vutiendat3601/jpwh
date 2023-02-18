package com.datvutech.jpwh.part2._7_mappingcollection.component;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Image {

    @Getter
    @Setter
    // @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    // @Column(name = "path", nullable = false)
    private String path;

    @Getter
    @Setter
    @Column(name = "width_cm")
    private int widthCm;

    @Getter
    @Setter
    @Column(name = "height_cm")
    private int heightCm;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Image other = (Image) o;
        if (!title.equals(other.title))
            return false;
        if (!path.equals(other.path))
            return false;
        if (widthCm != other.widthCm)
            return false;
        if (heightCm != other.heightCm)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + widthCm;
        result = 31 * result + heightCm;
        return result;
    }
}
