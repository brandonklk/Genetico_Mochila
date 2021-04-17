package org.catolica;

import java.util.List;
import java.util.Map;

public class People {

    private String name;
    private Integer backpackSize;
    private Integer genetics;
    final private Integer limitBackpack = 30;
    final private Double crossover = 0.6;
    final private Double mutation = 0.3;
    private List<Integer> gene;
    private List<People> allSons;
    private Map<String, Map<Integer, Integer>> items;

    public Double getCrossover() {
        return crossover;
    }

    public Double getMutation() {
        return mutation;
    }

    public List<People> getAllSons() {
        return allSons;
    }

    public void setAllSons(List<People> allSons) {
        this.allSons = allSons;
    }

    public Map<String, Map<Integer, Integer>> getItems() {
        return items;
    }

    public void setItems(Map<String, Map<Integer, Integer>> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGene() {
        return gene;
    }

    public void setGene(List<Integer> gene) {
        this.gene = gene;
    }

    public Integer getLimitBackpack() {
        return limitBackpack;
    }

    public Integer getGenetics() {
        return genetics;
    }

    public void setGenetics(Integer genetics) {
        this.genetics = genetics;
    }

    public Integer getBackpackSize() {
        return backpackSize;
    }

    public void setBackpackSize(Integer backpackSize) {
        this.backpackSize = backpackSize;
    }

}
