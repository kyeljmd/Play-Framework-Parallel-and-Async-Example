package controllers;

import java.util.List;

import models.Timing;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Parallel extends Controller {

	public static Result index() {
		// A Promise that will be redeemed when the 3 parallel
		//Http request are done
		Promise<List<Timing>> all = Promise.sequence(
				Timing.timedRequest("http://www.google.com"),
				Timing.timedRequest("http://www.yahoo.com"),
				Timing.timedRequest("http://www.reddit.com"));

		return async(all.map(new Function<List<Timing>, Result>() {
			@Override
			public Result apply(List<Timing> timings) throws Throwable {
				return ok(Json.toJson(timings));
			}
		}));
	}
}
