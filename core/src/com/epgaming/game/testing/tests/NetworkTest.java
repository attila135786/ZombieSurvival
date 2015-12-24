package com.epgaming.game.testing.tests;

import com.epgaming.game.testing.tests.TestModel.TestRequest;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by BeastProgrammer on 12/4/2015.
 */
public class NetworkTest {

	@Test
	public void playing() throws IOException {

		Server server = new Server();
		server.start();
		server.bind(54555, 54777);

		server.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof TestRequest) {
					TestRequest request = (TestRequest) object;
					System.out.println(request.getText());

					TestRequest response = new TestRequest();
					response.setText("Thanks");
					connection.sendTCP(response);
				}
			}
		});

		Client client = new Client();
		client.start();
		client.connect(5000, "192.168.0.4", 54555, 54777);


		TestRequest request = new TestRequest();
		request.setText("Here is the request");
		client.sendTCP(request);
	}
}
