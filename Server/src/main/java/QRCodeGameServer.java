import com.esotericsoftware.kryonet.*;

import data.*;
import data.Network.CreateGame;
import data.Network.Lobby;
import games.CaptureTheFlag;
import models.Game;

import java.io.IOException;
import java.util.ArrayList;

public class QRCodeGameServer {

    private ArrayList<Game> games;

    Server server;

    public QRCodeGameServer() {
        games = new ArrayList<Game>();
        server = new Server() {
            protected Connection newConnection() {
                return new GameConnection();
            }
        };
        Network.register(server);

        server.addListener(new Listener() {
            public void received(Connection con, Object obj) {
                GameConnection gc = (GameConnection)con;

                if (obj instanceof CreateGame) {
                    CreateGame createGame = (CreateGame)obj;
                    System.out.println(createGame.userName + " has created a new game");

                    if (createGame.game == 0) {
                        CaptureTheFlag game = new CaptureTheFlag();
                        games.add(game);
                    }

                    Lobby lobby = new Lobby();
                    lobby.noTeam = new String[] {createGame.userName};
                    server.sendToAllTCP(lobby);
                }
            }

            public void disconnected(Connection con) {

            }
        });

        try {
            server.bind(Network.PORT);
            server.start();
        } catch (IOException ex) {

        }
    }

    public static void main(String[] args) {
        new QRCodeGameServer();
    }
}
