package com.bornlotus.diycodeimitation.activity.api.module.topic;

public class Abilities {

    private boolean update;
    private boolean destroy;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "update=" + update +
                ", destroy=" + destroy +
                '}';
    }
}
