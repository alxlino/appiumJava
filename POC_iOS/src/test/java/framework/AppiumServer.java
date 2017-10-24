package framework;

import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	
	public void startServer() {
	
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("0.0.0.0");
		builder.usingPort(4723);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
		//Start the server with the builder
		//service = AppiumDriverLocalService.buildService(builder);
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
	
	public void stopServer() {
		service.stop();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		AppiumServer appiumServer = new AppiumServer();
		appiumServer.startServer();
		Thread.sleep(5000);
		appiumServer.stopServer();
	}
}
