package com.example.homework49.model;

import com.example.homework49.service.FileService;

import java.util.List;

public class CandidatesDataModel {
    private List<Candidate> candidates;

    public CandidatesDataModel() {
        this.candidates = FileService.readCandidatesFile();
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
