package com.voting.votingapp.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable // Tells JPA this class can be embedded in another entity (like inside Poll)
public class OptionVote {
    private String optionText;
    private Long voteCount = 0L;

}
