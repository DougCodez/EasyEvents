package easyevents.dougcodez;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventManager {

    private Map<Method, Object> listenerMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public void registerListener(Object listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (determineChecks().test(listener)) {
                listenerMap.putIfAbsent(method, listener);
            }
        }
    }

    public void unregisterListener(Object listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (determineChecks().test(listener)) {
                listenerMap.remove(method);
            }
        }
    }

    public void callListener(Event event) {
        List<EventPriority> orderedPriorities = Arrays.stream(EventPriority.values())
                .filter(priority -> priority.getPriority() <= priority.getPriority())
                .collect(Collectors.toList());
        for (EventPriority priority : orderedPriorities) {
            if (priority == EventPriority.HIGHEST) {
                invokeListener(event);
                return;
            } else if (priority == EventPriority.HIGH) {
                invokeListener(event);
                return;
            } else if (priority == EventPriority.NORMAL) {
                invokeListener(event);
                return;
            } else if (priority == EventPriority.LOW) {
                invokeListener(event);
                return;
            } else if (priority == EventPriority.LOWEST) {
                invokeListener(event);
                return;
            }
        }

    }

    private void invokeListener(Event event) {
        for (Method method : listenerMap.keySet()) {
            if (method.getParameterCount() == 1 && method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                try {
                    method.invoke(listenerMap.get(method), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Predicate<Object> determineChecks() {
        return listener -> {
            for (Method method : listener.getClass().getMethods()) {
                if (method.getAnnotations() == null || method.getAnnotations().length == 0) {
                    continue;
                }

                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if (eventHandler == null) {
                    continue;
                }

                if (method.getParameterCount() != 1) {
                    throw new IllegalArgumentException("Method " + method.getName() + " has @EventHandler annotation but has " + method.getParameterCount() + " parameters");
                }

                Class<?> eventClass = method.getParameterTypes()[0];
                if (!Event.class.isAssignableFrom(eventClass)) {
                    throw new IllegalArgumentException("Method " + method.getName() + " has @EventHandler annotation but has " + eventClass.getName() + " as parameter");
                }
            }

            return true;
        };
    }
}
