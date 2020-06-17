package com.example.final_test.game.bean;

import java.util.ArrayList;
import java.util.List;


public class SnakeBean {
    private List<PointBean> snake  = new ArrayList<>();
    public List<PointBean> getSnake() {
        return snake;
    }
    public void setSnake(List<PointBean> snake) {
        this.snake = snake;
    }
}
