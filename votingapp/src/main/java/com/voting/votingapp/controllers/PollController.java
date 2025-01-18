package com.voting.votingapp.controllers;

import com.voting.votingapp.model.Poll;
import com.voting.votingapp.request.Vote;
import com.voting.votingapp.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // This annotation tells Spring this class will handle web/API requests
@RequestMapping("/api/polls")  // This sets up the base URL path

// Angular (port 4200) tries to call Spring Boot (port 8080)
// Without @CrossOrigin - Browser blocks it
// With @CrossOrigin - Browser allows it because you specified it's OK
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {
    private final PollService pollService;

    // This is called "Constructor Injection" - Spring automatically provides the PollService
    // Spring sees this and knows:
    // "I need to provide a PollService when creating PollController"
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    // @PostMapping means this method handles POST requests
    // @RequestBody tells Spring to convert incoming JSON to a Poll object
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll); // Works safely!
    }
    // Handles GET requests to /api/polls endpoint
    @GetMapping
    // - List<Poll>: returns a list of Poll objects
    // - getAllPolls(): method name
    public List<Poll> getAllPolls() {
        // Uses pollService to:
        // - Get all polls from database
        // - Convert them to Poll objects
        // - Return them as a List
       return pollService.getAllPolls();
    }

    @GetMapping("/{id}") // Handles GET requests to /api/polls/1, /api/polls/2, etc.
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) { // @PathVariable takes id from URL
        return pollService.getPollById(id)// Ask service to find poll with this id
                .map(ResponseEntity::ok) // If found, wrap in 200 OK response
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404

    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }


}
