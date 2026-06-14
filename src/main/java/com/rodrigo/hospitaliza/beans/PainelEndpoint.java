package com.rodrigo.hospitaliza.beans;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rodrigo.hospitaliza.dtos.ChamadaEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/painel/{canal}")
public class PainelEndpoint {

    private static Map<String, List<Session>> canais = new ConcurrentHashMap<>();

    
    public void onChamada(@Observes ChamadaEvent chamada) {
    	String json = JsonbBuilder.create().toJson(chamada);
    	
    	canais.values().forEach(sessoes -> {
    		sessoes.removeIf(s -> !s.isOpen());
    		sessoes.forEach(s -> {
    			s.getAsyncRemote().sendText(json);
    		});
    	});
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("canal") String canal) {
    	canais.computeIfAbsent(canal, k -> new CopyOnWriteArrayList<>()).add(session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("canal") String canal) {
    	List<Session> sessoes = canais.get(canal);
        if (sessoes != null) {
            sessoes.remove(session);
            if (sessoes.isEmpty()) canais.remove(canal);
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
                sessao.getAsyncRemote().sendText(messageJson);
            } else {
                sessoesDoCanal.remove(sessao);
            }
        }
    }
}