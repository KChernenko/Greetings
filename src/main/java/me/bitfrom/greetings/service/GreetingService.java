package me.bitfrom.greetings.service;

import me.bitfrom.greetings.model.Greeting;

import java.util.Collection;

/**
 * Encapsulates all business logic
 */
public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(Long id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);
}