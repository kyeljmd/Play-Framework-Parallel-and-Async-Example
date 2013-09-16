package models;

import play.libs.F.Promise;
import play.libs.F.Function;
import play.libs.WS;
import play.libs.WS.Response;

public class Timing {

	public String url;
	public long latency;
	
	public Timing(String url, long latency){
		this.url = url;
		this.latency = latency;
	}
	
	public static Promise<Timing> timedRequest(final String url){
		final long start = System.currentTimeMillis();
		Promise<Response> resp = WS.url(url).get();
		
		return resp.map(new Function<Response,Timing>(){
			@Override
			public Timing apply(Response response) throws Throwable {
				long latency = System.currentTimeMillis() - start;
				return new Timing(url,latency);
			}
		});
		
	}
}
