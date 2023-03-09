package com.example.homework49.controller;

import com.example.homework49.model.Candidate;
import com.example.homework49.model.CandidatesDataModel;
import com.example.homework49.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidatesController {
    private static Candidate candidate;
    @RequestMapping(value = "/")
    public String CandidatesController(Model model){
        model.addAttribute("candidates", new CandidatesDataModel());
        return "candidates";
    }
    @PostMapping("/vote")
    public String vote(@RequestParam("candidateName") String candidateName) {
        List<Candidate> candidates = FileService.readCandidatesFile();
        for(int i = 0; i < FileService.readCandidatesFile().size(); i++){
            if (FileService.readCandidatesFile().get(i).getName().equals(candidateName)){
                candidates.get(i).setVotes();
                FileService.writeCandidatesFile(candidates);
                candidate = candidates.get(i);
            }
        }
        for (int i = 0; i < FileService.readCandidatesFile().size(); i++){
            candidates.get(i).setPercentage();
            FileService.writeCandidatesFile(candidates);
        }
        return "redirect:/thankyou";
    }
    @RequestMapping(value = "/thankyou")
    public String ThanksController(Model model){
        model.addAttribute("candidate", candidate);
        return "thankyou";
    }
    @RequestMapping(value = "/votes")
    public String votesController(Model model){
        model.addAttribute("candidates", new CandidatesDataModel());
        return "votes";
    }
}
