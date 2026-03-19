package com.rodrigo.hospitaliza.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/painel/{canal}")
public class PainelEndpoint {

    private static Map<String, List<Session>> canais = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("canal") String canal) {

        System.out.println("Nova conexão da tela: " + session.getId() + " para o canal: " + canal);

        List<Session> sessoesDoCanal =
                canais.computeIfAbsent(canal, k -> new CopyOnWriteArrayList<>());

        sessoesDoCanal.add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("canal") String canal) {

        List<Session> sessoesDoCanal = canais.get(canal);

        if (sessoesDoCanal != null) {
            sessoesDoCanal.remove(session);

            System.out.println("Canal '" + canal + "' agora tem "
                    + sessoesDoCanal.size() + " telas ativas.");
        }
    }

    @OnMessage
    public void onMessage(String messageJson, Session session, @PathParam("canal") String canal) {

        List<Session> sessoesDoCanal = canais.get(canal);

        if (sessoesDoCanal == null || sessoesDoCanal.isEmpty()) {
            return;
        }

        for (Session sessao : sessoesDoCanal) {
            if (sessao.isOpen()) {
                try {
                    sessao.getBasicRemote().sendText(messageJson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}