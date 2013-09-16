package controllers;

import play.Logger;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Controller;
import play.mvc.Result;

public class Proxy extends Controller {

	public static Result index(String url) {
		// Non-Blocking HTTP Call
		Logger.info("Before HTTP Call");
		Promise<Response> response = WS.url(url).get();
		
		//Transforms Asychronously  into a Play result
		Promise<Result> result = response.map(toResult);
		Logger.info("After HTTP Call");
		return async(result);
	}

	private static Function<Response, Result> toResult = new Function<Response, Result>() {
		@Override
		public Result apply(Response resp) throws Throwable {
			Logger.info("Inside the ToResult Function");
			return ok(resp.getBody()).as("text/html");
		}
	};

}
