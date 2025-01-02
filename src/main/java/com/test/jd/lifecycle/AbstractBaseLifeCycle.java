package com.test.jd.lifecycle;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-01 17:56:00
 */
public abstract class AbstractBaseLifeCycle implements LifeCycle {

    protected volatile LifeCycleStatusEnum status = LifeCycleStatusEnum.NEW;

    protected List<LifeCycleListener> listenerList = new CopyOnWriteArrayList<>();


    @Override
    public final synchronized void init() throws LifeCycleException {
        if(status != LifeCycleStatusEnum.NEW) {
            throw new LifeCycleException("init call status is not new");
        }
        initInternal();
        status = LifeCycleStatusEnum.INIT;
        fireLifecycleEvent(status,null);
    }

    @Override
    public final synchronized void start() throws LifeCycleException {

    }

    @Override
    public final synchronized void stop() throws LifeCycleException {

    }

    @Override
    public final synchronized void destroyed() throws LifeCycleException {

    }

    protected abstract void initInternal();

    protected void fireLifecycleEvent(LifeCycleStatusEnum status, Object dara) {
        LifeCycleEvent lifeCycleEvent = new LifeCycleEvent(this, dara, status);
        for (LifeCycleListener lifeCycleListener : listenerList) {
            lifeCycleListener.onEvent(lifeCycleEvent);
        }
    }

    protected void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        listenerList.add(lifeCycleListener);
    }

    protected void removeLifeCycleListener(LifeCycleListener lifeCycleListener) {
        listenerList.remove(lifeCycleListener);
    }

    protected List<LifeCycleListener> getListenerList() {
        return listenerList;
    }

}
