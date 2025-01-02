package com.test.jd.lifecycle;

/**
 * @author :panligang
 * @description :
 * @create :2023-09-05 14:58:00
 */
public class Server extends AbstractBaseLifeCycle {

    @Override
    protected void initInternal() {
        System.out.println(" server init ....");
    }

    public Server() {
        addLifeCycleListener(this::processEvent);
    }

    private void processEvent(LifeCycleEvent event) {
        LifeCycleStatusEnum status = event.getStatus();
        switch (status){
            case NEW:
                processNew();
                break;
            case INIT:
                processInit();
                break;
        }
    }

    private void processInit() {
        System.out.println(".....server init....");
    }

    private void processNew() {
    }

    public static void main(String[] args) throws LifeCycleException {
        LifeCycle lifeCycle = new Server();
        lifeCycle.init();;
    }
}
