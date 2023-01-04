package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.RandNoException;
import com.example.demo.models.Generate;

@Controller
@RequestMapping(path="/rand")
public class GenRandNoController {

    @GetMapping(path="/show")
    public String showRandForm(Model model) {

        /**
         * redirect to the generate.html
         * and show the input form
         */

        // instantiate the generate object
        // bind the generateObj to the text field
        Generate g = new Generate();
        model.addAttribute("generateObj", g);
        return "generate";
    }

    @PostMapping(path="/generate")
    public String postRandNum(@ModelAttribute Generate generate, Model model) {
        // TO-DO 
        this.randomizeNum(model, generate.getNumberVal());
        return "result";
    }

    private void randomizeNum(Model model, int noOfGenerateNo) {
        int maxGenNo = 30;
        String[] imgNumbers = new String[maxGenNo];

        // validate only accept gt 0 lte 30
        if (noOfGenerateNo < 0 || noOfGenerateNo > 30) {
            throw new RandNoException();
        }

        //generate all the number images and store it 
        // to the imgnumbers array
        for (int i = 0; i < maxGenNo ; i++ ) {
            imgNumbers[i] = "number" + i + ".jpg";
        }

        List<String> selectedImg = new ArrayList<String>();

        Random rnd = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
        while(uniqueResult.size() < noOfGenerateNo){
            Integer resultOfRand = rnd.nextInt(maxGenNo);
            uniqueResult.add(resultOfRand);
        }

        Iterator<Integer> it = uniqueResult.iterator();
        Integer currElem = null; 
        while(it.hasNext()){
            currElem = it.next();
            selectedImg.add(imgNumbers[currElem.intValue()]);
        }

        model.addAttribute("numberRandomNum", noOfGenerateNo);
        model.addAttribute("randomResult", selectedImg);

    }

    
}
