package org.academiadecodigo.shellmurais.wolfmother.model;

public enum Interests {
    FOOD(4),
    FOOTBALL(2),
    STUDY(6),
    PARTYING(1),
    GAMING(3),
    SPORTS(5);

    Interests(Integer value){
        this.value = value;
    }

    private Integer value;
}
