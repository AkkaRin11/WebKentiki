package com.kentiki.webkentiki.controller;

import com.kentiki.webkentiki.model.Answer;
import com.kentiki.webkentiki.repository.ImgRepository;
import com.kentiki.webkentiki.service.ImgService;
import com.kentiki.webkentiki.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@AllArgsConstructor
public class WebController {

    private final ImgService imgService;
    private final ImgRepository imgRepository;
    private final S3Service s3ervice;


    @GetMapping("/")
    public String mainPage(){
        return "mainPage";
    }

    @GetMapping("/up")
    public ResponseEntity downloadNewImg(){
        s3ervice.downloadNewImg();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/put/{name}/{date}")
    public ResponseEntity putNewDate(@PathVariable String name, @PathVariable String date){
        imgService.putDate(name, date);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/right")
    public String rightAnswer() {
        return "rightAnswerPage";
    }

    @GetMapping("/wrong/{imgName}")
    public String wrongAnswer(Model model, @PathVariable String imgName) {
        model.addAttribute("date", imgService.getRightAnswer(imgName));
        return "wrongAnswerPage";
    }

    @GetMapping("/quiz")
    // тут нужно давать рандомное имя картинки
    public String quiz() {
        return "redirect:/quiz/" + imgRepository.getRandomNameOfImg();
    }

    @GetMapping("/quiz/{imgName}")
    public String quizWithPar(Model model, @PathVariable String imgName) {
        model.addAttribute("action",  imgName);
        model.addAttribute("imgName", imgService.getImgName(imgName));
        model.addAttribute("answer", new Answer());
        return "quizPage";
    }

    @PostMapping("/quiz/{imgName}")
    public String checkAnswer(@ModelAttribute Answer answer, @PathVariable String imgName) {
        if (imgService.isAnswerTrue(answer, imgName)){
            answer.resetDate();
            return "redirect:/right";
        } else {
            answer.resetDate();
            return "redirect:/wrong/" + imgName;
        }
    }
}
