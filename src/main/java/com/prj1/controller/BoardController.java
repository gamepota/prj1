package com.prj1.controller;

import com.prj1.domain.Board;
import com.prj1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/add")
    public String add() {
        return "board/add";
    }

    @PostMapping("/add")
    public String addPost(Board board, RedirectAttributes rttr) {

        System.out.println("board = " + board);

        service.add(board);
//        return "redirect:/board?id=" + board.getId(); -> 간단한건 위에 리다이렉트 업이 이렇게 작성해도됨 권장 되진않음
        rttr.addAttribute("id", board.getId());
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String view(Integer id, Model model) {
        Board board = service.get(id);


        model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("boardList", service.list());
        return "board/home";
    }


}
