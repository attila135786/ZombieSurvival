package com.epgaming.game.testing.tests;

import com.epgaming.game.testing.tests.TestModel.TestRequest;
import com.epgaming.game.testing.tests.TestModel.TestResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

/**
 * Created by BeastProgrammer on 12/4/2015.
 */
public class ServerNetworkTest
{
    public ServerNetworkTest() throws IOException {
        Server server = new Server();
        registerModelWithKryo(server);
        initializeListeners(server);
        server.start();
        server.bind(54555, 54777);
        System.out.println("Server listening");
    }

    public void initializeListeners(Server server){
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
    }


    // TODO: move this out of here asap
    public void registerModelWithKryo(Server server){
        Kryo kryo = server.getKryo();
        kryo.register(TestRequest.class);
        kryo.register(TestResponse.class);
    }
}
