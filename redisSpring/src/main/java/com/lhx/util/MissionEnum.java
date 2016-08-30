package com.lhx.util;

/**
 * Created by lhx on 16-6-17 下午2:36
 *
 * @Description
 */
public enum MissionEnum {

    A(1),

    B(2);


    private long id;
    private Integer value;


    MissionEnum(long id) {
        this.id = id;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
