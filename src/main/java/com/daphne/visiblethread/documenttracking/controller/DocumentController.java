package com.daphne.visiblethread.documenttracking.controller;


import com.daphne.visiblethread.documenttracking.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class DocumentController {


    private final DocumentService service;

    DocumentController(DocumentService service) {
        this.service = service;
    }

//    /**
//     * Get users docs.
//     *
//     * @param doc doc
//     * @return AccountInfo containing users account info.
//     */
//    @GetMapping("/user/balance")
//    AccountInfo getBalance(@RequestParam Integer pin) {
//        log.info(String.valueOf(pin));
//        try {
//            return service.getBalance(pin);
//        } catch (AccessDeniedException exc) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to dispense funds. " + exc.getMessage());
//        }
//    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("email") String userEmail,
                                   RedirectAttributes redirectAttributes) {

        try {
            service.store(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping("/wordFrequency")
    public Map<String, Integer> handleWordFrequency(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            Map<String, Integer> wordFreq = service.findWordFrequency(file);
            return wordFreq;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

//        return null;
    }
}
