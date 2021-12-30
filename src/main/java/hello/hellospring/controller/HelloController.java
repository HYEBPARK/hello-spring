package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 웹 첫번째 진입점이 Controller
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello~");
        return "hello";
        // viewResolver가 화면을 찾아서 처리한다.
        // 스프링 부트 템플릿엔진 기본 viewName 매핑
        // `resources/templates/`+{hello}+`.html`
        // spring-boot-devtools 라이브러리를 추가하면 html 파일을 컴파일만 해주고 서버 재시작없이 view 변경 가능 !!
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }


    //API 방식
    @GetMapping("hello-string")
    @ResponseBody // http 응답 body에 직접 넣어 주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; // "hello name"
    }

    //API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 객체가 return 된다면 HttpmESSAGEcONVRTER가 동작
        // 기본 문자 처리 : StringHttpMessageConverter
        // 기본 객체 처리 : MappingJackson2HttpMessageConverter

    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
