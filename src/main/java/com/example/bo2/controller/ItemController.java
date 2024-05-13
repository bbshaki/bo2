package com.example.bo2.controller;

import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Log4j2
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String registerPost(@Valid ItemDTO itemDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            return "redirect:/item/register";
        }
        Long ino = itemService.register(itemDTO);
        redirectAttributes.addFlashAttribute("result", ino);
        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ItemDTO> pageResponseDTO = itemService.list(pageRequestDTO);
        model.addAttribute("list", pageResponseDTO);
    }

    @GetMapping({"/detailed", "/modify"})
    public void detailed(Long ino, Model model,
                         PageRequestDTO pageRequestDTO){
        model.addAttribute("dto", itemService.detailed(ino));
    }

    @PostMapping("/modify")
    public String modify(@Valid ItemDTO itemDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO){
        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute
                    ("errors", bindingResult.getAllErrors());
            log.info("오류발생!!!!!!!!!" + bindingResult.getAllErrors());
            redirectAttributes.addAttribute("ino", itemDTO.getIno());
            return "redirect:/item/modify?"+pageRequestDTO.getLink();
        }
        itemService.modify(itemDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("ino", itemDTO.getIno());
        return "redirect:/item/detailed?"+pageRequestDTO.getLink();

    }
    @PostMapping("/delete")
    public String delete(Long ino){
        itemService.delete(ino);
        return "redirect:/item/list";
    }


}
