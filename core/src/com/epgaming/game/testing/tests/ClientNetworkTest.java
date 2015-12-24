package com.epgaming.game.testing.tests;

import com.epgaming.game.testing.tests.TestModel.TestRequest;
import com.epgaming.game.testing.tests.TestModel.TestResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class ClientNetworkTest {

	public ClientNetworkTest() throws IOException {
		Client client = new Client();

		registerModelWithKyro(client);
		initializeListeners(client);

		client.start();
		String IP = "192.168.0.2";
		client.connect(5000, IP, 54555, 54777);
		System.out.println("Client connected to ");
		TestRequest request = new TestRequest();
		request.setText("Here is the request");
		client.sendTCP(request);
	}

	private void initializeListeners(Client client) {
		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof TestRequest) {
					TestRequest request = (TestRequest) object;
					System.out.println(request.getText());
				}
			}
		});
	}

	public void registerModelWithKyro(Client client)
	{
		Kryo kryo = client.getKryo();
		kryo.register(TestRequest.class);
		kryo.register(TestResponse.class);
	}

}
