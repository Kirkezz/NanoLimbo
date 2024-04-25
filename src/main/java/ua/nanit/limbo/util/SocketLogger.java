package ua.nanit.limbo.util;

import ua.nanit.limbo.server.Logger;
import java.net.*;
import java.io.*;

public class SocketLogger {
    private String host;
    private int port;
    private Socket socket = null;
    private PrintWriter outStream = null;

    public SocketLogger(String host, int port) {
        this.host = host;
        this.port = port;
        setUp();
    }

    private void setUp() {
        try {
            socket = new Socket(host, port);
            outStream = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            Logger.warning("Cannot find host called: " + host);
        } catch (IOException e) {
            Logger.warning("Could not establish connection for " + host);
        }
    }

    public void sendMessage(String message) {
        if (socket != null && outStream != null) {
            outStream.println(message);
        } else {
            Logger.warning("Socket connection not established.");
        }
    }

    public void cleanUp() {
        try {
            if (outStream != null)
                outStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            Logger.warning("Error in cleanup");
        }
    }
}