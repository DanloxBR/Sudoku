package com.bringto.sudoku.service;

import java.util.*;

public class NotifierService {

    private static NotifierService instance;

    public static NotifierService getInstance() {
        if (instance == null) {
            instance = new NotifierService();
        }
        return instance;
    }

    private final Map<EventEnum, List<EventListener>> listeners =
            new HashMap<>();

    private NotifierService() {
        for (EventEnum e : EventEnum.values()) {
            listeners.put(e, new ArrayList<>());
        }
    }

    public void subscribe(EventEnum event, EventListener listener) {
        if (!listeners.containsKey(event)) {
            listeners.put(event, new ArrayList<>());
        }

        List<EventListener> list = listeners.get(event);

        if (!list.contains(listener)) {
            list.add(listener);
        }
    }

    public void notify(final EventEnum eventType, final EventData data) {
        List<EventListener> selected = listeners.get(eventType);

        if (selected == null) return;

        for (EventListener l : selected) {
            l.update(eventType, data);
        }
    }
}
