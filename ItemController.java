package com.campusfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository repo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", repo.findAll());
        return "index";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String type, Model model) {
        model.addAttribute("items", repo.findByItemType(type));
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("items",
                repo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword));
        return "index";
    }

    @GetMapping("/report")
    public String reportForm(Model model) {
        model.addAttribute("item", new Item());
        return "report";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item) {
        item.setDateAdded(LocalDate.now().toString());
        repo.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}