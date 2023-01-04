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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Generate;

@Controller
@RequestMapping(path="/")
public class RandomNumberController {
    
    @GetMapping("/show")
    public String generateShowForm(Model model){
        Generate numberOfImages = new Generate();
        model.addAttribute("userNumberInput", numberOfImages);
        return "generate";
    }

    @PostMapping("/generate")
    public String displayRandomImages(Generate generate, Model model) {
        this.randomizeImage(model, generate.getNumberValue());
        System.out.println(generate.getNumberValue());
        model.addAttribute("userNumberInput", generate.getNumberValue());
        return "result";
    }

    private void randomizeImage(Model model, int userNumberInput) {
        System.out.println("test");
        int maxNumber = 30;
        Random rnd = new Random();

        String[] allImagesList = new String[maxNumber];
        for (int i = 0; i < maxNumber; i++) {
            allImagesList[i] = "number" + i +".jpg";
        }

        List<String> uniqueImageList = new ArrayList<String>();

        Set<Integer> uniqueNumbers = new LinkedHashSet<Integer>();
        while (uniqueNumbers.size() < userNumberInput) {
            uniqueNumbers.add(rnd.nextInt(maxNumber));
        }

        Iterator<Integer> it = uniqueNumbers.iterator();

        while(it.hasNext()){
            uniqueImageList.add(allImagesList[it.next()]);
        }


        model.addAttribute("userNumberInput", userNumberInput);
        model.addAttribute("imageList", uniqueImageList);

    }
}
