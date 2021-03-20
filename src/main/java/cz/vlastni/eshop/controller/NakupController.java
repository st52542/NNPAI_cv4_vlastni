package cz.vlastni.eshop.controller;

import cz.vlastni.eshop.repository.DopravaRepository;
import cz.vlastni.eshop.repository.PlatbaRepository;
import cz.vlastni.eshop.service.INakupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NakupController {

    private final INakupService iNakupService;
    private final DopravaRepository dopravaRepository;
    private final PlatbaRepository platbaRepository;

    public NakupController(INakupService iNakupService, DopravaRepository dopravaRepository, PlatbaRepository platbaRepository) {
        this.iNakupService = iNakupService;
        this.dopravaRepository = dopravaRepository;
        this.platbaRepository = platbaRepository;
    }
    @PostMapping("/checkout")
    public String checkoutPost(@RequestParam String platba,@RequestParam String doprava){

return null;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("dopravy",dopravaRepository.findAll());
        model.addAttribute("platby",platbaRepository.findAll());
        iNakupService.checkout();
        return "checkout";
    }
}
