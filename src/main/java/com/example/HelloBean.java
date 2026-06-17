package com.example;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HelloBean {
    private String name = "Világ";
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void greetings() { this.name = "Üdvözöllek, " + this.name + "!"; }
}
