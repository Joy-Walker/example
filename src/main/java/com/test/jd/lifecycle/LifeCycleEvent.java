package com.test.jd.lifecycle;

import java.util.EventObject;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-05 14:17:00
 */
public class LifeCycleEvent extends EventObject {

    private final Object data ;

    private final LifeCycleStatusEnum status;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public LifeCycleEvent(LifeCycle source,Object data,LifeCycleStatusEnum status) {
        super(source);
        this.data = data;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public LifeCycleStatusEnum getStatus() {
        return status;
    }
}
