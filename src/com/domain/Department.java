package com.domain;

// Create Department modal
public class Department extends AuditModel implements Comparable<Department> {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name +
                ", creted_at="+ getCreatedAt() +
                ", updated_at="+ getUpdatedAt();
    }

    @Override
    public int compareTo(Department o) {
//        return this.getName()>o.getName() ? 1 : this.getName() < o.getName() ? -1 : 0;
//        return this.getName().compareTo(o.getName());
        return o.getName().compareTo(this.getName());
    }
}
