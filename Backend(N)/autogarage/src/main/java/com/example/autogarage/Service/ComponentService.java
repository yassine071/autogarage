package com.example.autogarage.Service;

import com.example.autogarage.model.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ComponentService {
    public abstract long createComponent(Component action);
    public abstract void updateComponent(long id, Component action);
    public abstract void partialUpdateComponent(long id, Map<String, String > fields, Map<Double, Double>fields2);
    public abstract void deleteComponent(long id);
    public abstract Collection<Component> getComponents();
    public abstract Optional<Component> getComponentById(long id);
}
