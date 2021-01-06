package com.krytest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.IOException;
import java.util.Date;

@Entity
@Table(name = "endpoints")
@JsonPropertyOrder(alphabetic = true)
@EntityListeners(AuditingEntityListener.class)
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Endpoint() {
    }

    @Column(name = "url")
    private String url;

    @Column(name = "state")
    private boolean active;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created")
    public Date created;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated")
    public Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Endpoint(String url) {
        this.url = url;
        this.active = false;
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated = new Date();
    }
}

