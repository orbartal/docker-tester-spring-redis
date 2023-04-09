package orbartal.springbootdemoredistester.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//An ugly but working work around to set config properties.
@Component
public class DemoProptiesUtil {
	
    private static String DEMO_URL;

    @Value("${demo.backend.url}")
    public void setDemoUrlStatic(String url){
    	DemoProptiesUtil.DEMO_URL = url;
    }

	public static String getDemoUrl() {
		return DEMO_URL;
	}

}
