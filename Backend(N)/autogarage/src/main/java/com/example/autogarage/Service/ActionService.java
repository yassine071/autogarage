package com.example.autogarage.Service;


import com.example.autogarage.model.Action;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ActionService {
    public abstract long createAction(Action action);
    public abstract void updateAction(long id, Action action);
    public abstract void partialUpdateAction(long id, Map<String, String > fields, Map<Double, Double>fields2);
    public abstract void deleteAction(long id);
    public abstract Collection<Action> getActions();
    public abstract Optional<Action> getActionById(long id);


}
