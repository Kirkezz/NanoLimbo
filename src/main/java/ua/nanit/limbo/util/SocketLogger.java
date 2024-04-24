package ua.nanit.limbo.util;

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
            System.err.println("Cannot find host called: " + host);
        } catch (IOException e) {
            System.err.println("Could not establish connection for " + host);
        }
    }

    public void sendMessage(String message) {
        if (socket != null && outStream != null) {
            outStream.println(message);
        } else {
            System.err.println("Socket connection not established.");
        }
    }

    public void cleanUp() {
        try {
            if (outStream != null)
                outStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            System.err.println("Error in cleanup");
        }
    }
}