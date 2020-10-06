package com.ethicsinc.server;

public class BaseId {
    private final long id;

    public BaseId(long id) {
        this.id = id;
    }

    public long value() {
        return id;
    }

    public boolean equals(BaseId rhs){
        return this.id == rhs.value();
    }
}
