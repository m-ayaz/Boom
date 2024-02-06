package com.boom.test;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class  TESTPROP<T> extends ObjectProperty<T> {
    @Override
    public void addListener(ChangeListener<? super T> changeListener) {

    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(ChangeListener<? super T> changeListener) {

    }

    @Override
    public void bind(ObservableValue<? extends T> observableValue) {

    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public void set(T t) {

    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void unbind() {

    }

    @Override
    public boolean isBound() {
        return false;
    }
}
