package ru.job4j.question;

import java.util.*;

public class Analize {
    public static void fillMap(Map<Integer, String> map, Set<User> set) {
        for (User user : set) {
            map.put(user.getId(), user.getName());
        }
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        HashMap<Integer, String> pMapUsers = new HashMap<>(previous.size());
        HashMap<Integer, String> cMapUsers = new HashMap<>(current.size());
        fillMap(pMapUsers, previous);
        fillMap(cMapUsers, current);
        for (User user : current) {
            if (pMapUsers.containsKey(user.getId())) {
                if (!pMapUsers.get(user.getId()).equals(user.getName())) {
                    int changed = info.getChanged();
                    info.setChanged(++changed);
                }
            } else {
                int added = info.getAdded();
                info.setAdded(++added);
            }
        }
        for (User user : previous) {
            if (!cMapUsers.containsKey(user.getId())) {
                int deleted = info.getDeleted();
                info.setDeleted(++deleted);
            }
        }
        return info;
    }
}