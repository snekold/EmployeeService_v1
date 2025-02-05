package org.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Robber {
    @Id
    @GeneratedValue
    private String name;
    private int stillSum;
    private int balance;

    public Robber(String name, int stillSum, int balance) {
        this.name = name;
        this.stillSum = stillSum;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getStillSum() {
        return stillSum;
    }

    public int getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStillSum(int stillSum) {
        this.stillSum = stillSum;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
