package me.bitfrom.greetings.service;

import me.bitfrom.greetings.model.Greeting;
import me.bitfrom.greetings.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
    @Cacheable(value = "greetings", key = "#id")
    public Greeting findOne(Long id) {
        return greetingRepository.findOne(id);
    }

    @Override
    @CachePut(value = "greetings", key = "#result.id")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Greeting create(Greeting greeting) {
        if (greeting.getId() != null) {
            //Can't create Greeting with specified id value
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    @CachePut(value = "greetings", key = "#greeting.id")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Greeting update(Greeting greeting) {
        Greeting persistedGreeting = findOne(greeting.getId());
        if (persistedGreeting == null) {
            //Can't update Greeting that hasn't been persisted
            return null;
        }
        return greetingRepository.save(greeting);
    }

    @Override
    @CacheEvict(value = "greetings", key = "#id")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        greetingRepository.delete(id);
    }

    @Override
    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache() {

    }
}