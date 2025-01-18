package com.voting.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity //Marks this class as a database table
@Data //Automatically generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor //Creates an empty constructor that takes no arguments
public class Poll {
    @Id // Marks this field as the primary key (unique identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells database to automatically generate ID numbers (1,2,3...)
    private Long id;
    private String question;

    @ElementCollection //Tells Spring to create a separate table for storing the list of options with votes
    private List<OptionVote> options =new ArrayList<>(); // Declares a list to store options with no.votes and initializes it as an empty ArrayList

    //@ElementCollection     // Creates a separate table for vote numbers in database
    //private List<Long> votes = new ArrayList<>();    // Creates a list to store vote counts, starting empty


}
