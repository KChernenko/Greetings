package me.bitfrom.greetings.service;

import me.bitfrom.greetings.model.Greeting;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingServiceBean implements GreetingService {

    private static Long nextId;
    private static Map<Long, Greeting> greetingMap;

    static {
        Greeting g1 = new Greeting();
        g1.setText("Hello world!");
        save(g1);

        Greeting g2 = new Greeting();
        g2.setText("Spring Boot!");
        save(g2);
    }

    @Override
    public Collection<Greeting> findAll() {
        return greetingMap.values();
    }

    @Override
    public Greeting findOne(Long id) {
        return greetingMap.get(id);
    }

    @Override
    public Greeting create(Greeting greeting) {
        return save(greeting);
    }

    @Override
    public Greeting update(Greeting greeting) {
        return save(greeting);
    }

    @Override
    public void delete(Long id) {
        remove(id);
    }

    @Nullable
    private static Greeting save(Greeting greeting) {
        if (greetingMap == null) {
            greetingMap = new HashMap<Long, Greeting>();
            nextId = 1L;
        }
        //Update
        if (greeting.getId() != null) {
            Greeting oldGreeting = greetingMap.get(greeting.getId());
            if (oldGreeting == null) {
                return null;
            }
            greetingMap.remove(greeting.getId());
            greetingMap.put(greeting.getId(), greeting);
            return greeting;
        }
        //Create
        greeting.setId(nextId);
        nextId += 1;
        greetingMap.put(greeting.getId(), greeting);
        return greeting;
    }

    private static boolean remove(Long id) {
        Greeting deletedGreeting = greetingMap.remove(id);
        return deletedGreeting != null;
    }
}