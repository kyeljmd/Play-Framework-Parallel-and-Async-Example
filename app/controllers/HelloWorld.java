package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

public class HelloWorld extends Controller{

    public static Result hello(String name, int age){
    	String location = Play.application().configuration().getString("location");
    	return ok(views.html.hello.render(name,age,location));
    }
}
