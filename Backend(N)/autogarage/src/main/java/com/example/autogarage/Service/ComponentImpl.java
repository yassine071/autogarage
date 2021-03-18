package com.example.autogarage.Service;

import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Component;
import com.example.autogarage.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class ComponentImpl implements ComponentService{
    @Autowired
    private ComponentRepository componentRepository;

    @Override
    public long createComponent(Component component) {
        Component newComponent = componentRepository.save(component);
        return newComponent.getId();
    }

    @Override
    public void updateComponent(long id, Component component) {
        if (!componentRepository.existsById(id)) throw new RecordNotFoundException();
        Component existingComponent = componentRepository.findById(id).get();
        existingComponent.setComponent(component.getComponent());
        existingComponent.setPrice(component.getPrice());
        componentRepository.save(existingComponent);
    }

    @Override
    public void partialUpdateComponent(long id, Map<String, String> fields, Map<Double,Double>fields2) {
        if (!componentRepository.existsById(id)) throw new RecordNotFoundException();
        Component component = componentRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "component":
                    component.setComponent((String) fields.get(field));
                    break;
                case "price":
                    component.setPrice((double) fields2.get(field));
                    break;
            }
        }
        componentRepository.save(component);
    }


    @Override
    public void deleteComponent(long id) {
        if (!componentRepository.existsById(id)) throw new RecordNotFoundException();
        componentRepository.deleteById(id);
    }

    @Override
    public Collection<Component> getComponents() {
        return componentRepository.findAll();
    }

    @Override
    public Optional<Component> getComponentById(long id) {
        if (!componentRepository.existsById(id)) throw new RecordNotFoundException();
        return componentRepository.findById(id);
    }
}
