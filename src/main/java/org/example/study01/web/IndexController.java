package org.example.study01.web;

import lombok.RequiredArgsConstructor;
import org.example.study01.service.posts.PostsService;
import org.example.study01.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다. final이 없는 필드는 생성자에 포함되지 않습니
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; // mustach starter 덕분에 알아서 src/main/resouces/template안에 *.mustache랑 return 값이랑 매핑 시켜서 해당 파일을 불러와 view resolver가 처리해준다
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
