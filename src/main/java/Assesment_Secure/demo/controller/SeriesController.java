package Assesment_Secure.demo.controller;

import Assesment_Secure.demo.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
public class SeriesController {
    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/series")
    public String series_form(Model model) {
        model.addAttribute("defaultN", 1000);
        return "series";
    }

    @PostMapping("/series")
    public String computeTerm(@RequestParam(defaultValue = "1000") int n, Model model) {
        try {
            BigInteger term = seriesService.computeNthTerm(n);
            model.addAttribute("term", term.toString());
            model.addAttribute("n", n);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "series";
    }
}

