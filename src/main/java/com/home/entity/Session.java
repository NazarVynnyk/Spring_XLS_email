package com.home.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Nazar on 24.06.2017.
 */
@Entity
@Table(name = "session")
public class Session implements Serializable {
    static final long serialVersionUID = 234523497L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
    public Set<Request> requests;

    @JoinColumn(name = "date_opened")
    private Timestamp dateOpened;

    @JoinColumn(name = "date_closed")
    private Timestamp dateClosed;

    public Session() {
    }

    public Session(User user, Set<Request> requests, Timestamp dateOpened, Timestamp dateClosed) {
        this.user = user;
        this.requests = requests;
        this.dateOpened = dateOpened;
        this.dateClosed = dateClosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> request) {
        this.requests = request;
    }

    public Timestamp getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Timestamp dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Timestamp getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed) {
        this.dateClosed = dateClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (!user.equals(session.user)) return false;
        if (!dateOpened.equals(session.dateOpened)) return false;
        return dateClosed.equals(session.dateClosed);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + dateOpened.hashCode();
        result = 31 * result + dateClosed.hashCode();
        return result;
    }
}
