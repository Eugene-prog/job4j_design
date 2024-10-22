package ru.job4j.question;

import java.util.Objects;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public int getChanged() {
        return changed;
    }

    public int getDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Info info)) {
            return false;
        }
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }

    @Override
    public String toString() {
        return "Info{" + "added=" + added + ", changed=" + changed + ", deleted=" + deleted + '}';
    }
}