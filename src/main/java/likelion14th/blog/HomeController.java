package likelion14th.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("/") // HTTP GET 요청 메서드 정의
    public String home(Model model) {
        // Model에 데이터를 추가하여 View로 전달
        model.addAttribute("message", "Welcome Likelion14th to Spring MVC!");
        return "home";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", defaultValue = "Likelion14th") String name, Model model) {
        // 쿼리 파라미터 "name"을 받아서 동적인 메시지 생성
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "greet";
    }

    // "/api/hello" URL로 접속 시 JSON 응답 (View 없이 데이터만 반환)
    @GetMapping("/api/hello")
    @ResponseBody
    public String helloApi() {
        return "Hello Likelion14th. Spring MVC API!";
    }
}