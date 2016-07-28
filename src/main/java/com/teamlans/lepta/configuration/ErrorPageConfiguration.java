package com.teamlans.lepta.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorPageConfiguration {

  @RequestMapping(value = "/*", method = RequestMethod.GET)
  @ResponseBody
  public String returnSomething() {
    return "<h1>Oops</h1><br><p>That's an invalid page. You probably entered an invalid URL :-(</p>";
  }
}