package shop.tripn.api.board.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.tripn.api.board.domain.Board;
import shop.tripn.api.board.repository.BoardRepository;
import shop.tripn.api.board.service.BoardServiceImpl;


import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BoardRepository boardRepository;
    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Board board) {
        Board b = board.toEntity();
        boardRepository.save(b);
        return ResponseEntity.ok("글쓰기 성공");
    }

    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/question/{question}")
    public ResponseEntity<List<Board>> findByQuestion(@PathVariable String question){
        logger.info(String.format("제목으로 찾기"));
        return ResponseEntity.ok(boardRepository.findByQuestion(question));
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<Board>> findKeywordSearch(@PathVariable String keyword){
        logger.info(String.format("키워드로 찾기"));
        return ResponseEntity.ok(boardRepository.findKeywordSearch(keyword));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> findAll() {
        logger.info(String.format("전체 조회 :" + boardRepository.findAll()));
        return ResponseEntity.ok(boardRepository.findAll());
    }

    @GetMapping("/page")
    public String list(HttpSession session, Model model, @RequestParam(required = false,defaultValue = "0", value = "page") int page){
        Page<Board> listPage = boardServiceImpl.list(page);
        int totalPage = listPage.getTotalPages();
        model.addAttribute("board", listPage.getContent());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNo", page);

        return "main/list";
    }
}
