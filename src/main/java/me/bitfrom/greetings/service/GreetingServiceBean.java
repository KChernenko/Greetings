package me.bitfrom.greetings.service;

import me.bitfrom.greetings.model.Greeting;
import me.bitfrom.greetings.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GreetingServiceBean implements GreetingService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingServiceBean(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Collection<Greeting> findAll() {
        return greetingRepository.findAll();
    }

    @Override
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    public Greeting create(Greeting greeting) {
        if (greeting.getId() != null) {
            //Can't create Greeting with specified id value
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    public Greeting update(Greeting greeting) {
        Greeting persistedGreeting = findOne(greeting.getId());
        if (persistedGreeting == null) {
            //Can't update Greeting that hasn't been persisted
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    public void delete(Long id) {
        greetingRepository.delete(id);
    }
}