package com.gnadenheimer.mg3;

import javafx.concurrent.Task;

public abstract class ViewLoaderTask<V> extends Task<V> {
    protected Class aClass;

    public ViewLoaderTask(Class aClass) {
        this.aClass = aClass;
    }
}
