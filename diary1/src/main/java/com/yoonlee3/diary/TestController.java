package com.yoonlee3.diary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/main")
	public String test() {return"mypageT";}
	@GetMapping("/Gmain2")
	public String test2() {return"Gmain2";}
	@GetMapping("/test")
	public String test3() {return"test";}
	@GetMapping("/testGroup")
	public String test4() {return"testGroup";}
	@GetMapping("/testMypage")
	public String test5() {return"testMypage";}
	@GetMapping("/detail")
	public String test6() {return"detailT";}
	@GetMapping("/write")
	public String test7() {return"writeT";}
	@GetMapping("/Gdetail")
	public String test8() {return"group_detailT";}
}
