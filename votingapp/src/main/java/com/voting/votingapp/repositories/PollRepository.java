package com.voting.votingapp.repositories;

import com.voting.votingapp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Repository - tells Spring this interface handles database operations
public interface PollRepository extends JpaRepository<Poll, Long> {
    // JpaRepository<Poll, Long> means:
    // - Poll: the type of object we're storing (our Poll class)
    // - Long: the type of ID field in Poll class
    // By extending JpaRepository, we automatically get these methods:
    // save(Poll poll)         - saves a poll to database
    // findById(Long id)       - finds a poll by its ID
    // findAll()              - gets all polls
    // delete(Poll poll)      - deletes a poll
    // ... and many more
    // We don't need to write any code here because
    // Spring creates all the standard database methods for us
    // and
    // JpaRepository provides all the actual database methods:
    // - Converts Java code to SQL statements
    // - Handles database connections
    // - Provides methods like save(), findById(), etc.
    // - Does the actual work of storing/retrieving data
}
