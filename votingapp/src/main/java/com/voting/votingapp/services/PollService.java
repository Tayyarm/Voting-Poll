package com.voting.votingapp.services;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.voting.votingapp.repositories.PollRepository;

import java.util.List;
import java.util.Optional;

@Service // Tells Spring this class contains business logic, THIS is what makes Spring do the injection automatically

public class PollService {

    private final PollRepository pollRepository;

    // Constructor injection
    // Spring does this for you behind the scenes:
    // PollRepository repo = new PollRepository();
    // PollService service = new PollService(repo);
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll){
        // This method actually creates the poll in the database
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        // This method gets all polls from database
        // Uses repository's findAll() method which Spring provides
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        // This method finds a specific poll by its ID
        // Returns Optional because poll might not exist
        // Optional helps us handle case when poll isn't found
        return pollRepository.findById(id);
    }

    public void vote(long pollId, int optionIndex) {
        //Get Poll from DataBase
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(()-> new RuntimeException("Poll not found"));
        // Get All Options
        List<OptionVote>options = poll.getOptions();

        // If index is not valid, throw error
        if(optionIndex < 0 || optionIndex>= options.size()) {
            throw new IllegalArgumentException("Invalid option index");


        }
        // Get Selected Option
        OptionVote selectedOption = options.get(optionIndex);

        // Increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        // Save incremented vote option into the database
        pollRepository.save(poll);

    }
}
