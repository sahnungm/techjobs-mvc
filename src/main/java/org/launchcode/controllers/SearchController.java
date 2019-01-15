package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")

public class SearchController extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("all","all");

        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String result(Model model,@RequestParam String searchType,@RequestParam String searchTerm){
        ArrayList<HashMap<String,String>> jobs = new ArrayList<>();
        if (!searchTerm.isEmpty()) {
            if (searchType.equals("all")){
                jobs = findByValue(searchTerm);
            }else {
                jobs = findByColumnAndValue(searchType, searchTerm);
            }
        }
        for(HashMap<String,String> job : jobs) {
            System.out.println((searchType));
        }
        model.addAttribute("all",searchType);
        model.addAttribute("jobs",jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
