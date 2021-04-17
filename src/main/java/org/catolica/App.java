package org.catolica;

import java.util.*;

public class App
{
    public static void main( String[] args ) {


        Integer maxGenerations = 10000;
        Integer populationInitial = 4;
        Integer generation = 0;
        List<People> peoples = createPeople(populationInitial, false);
        List<People> peoplesForCreateSons = new ArrayList<>();
        List<People> sonsOfParents = new ArrayList<>();

        while (generation < maxGenerations) {
            peoples.add(buildItemAndCreatePeople(false));
            generation++;
        }

        for (People people: peoples) {
            peoplesForCreateSons.add(people);

            if (peoplesForCreateSons.size() == 2) {
                sonsOfParents.addAll(createSonsForParents(peoplesForCreateSons));
                peoplesForCreateSons.clear();
            }
        }

        peoples.addAll(sonsOfParents);

        displayPeoplesCreated(peoples);

    }

    private static void displayPeoplesCreated(List<People> peoples) {

        for (People people : peoples) {
            System.out.println("Criado pessoa, " + people.getName() + " com "+ people.getItems().size() +
                    " items, sendo que o peso da mochila é de " + people.getBackpackSize() + " e" +
                    (people.getAllSons() != null ? people.getAllSons().size() : " não tem") + " filhos ");
        }
        System.out.println("Total de pessoas: "+ peoples.size());;
    }

    public static List<People> createSonsForParents(List<People> parents) {
        List<People> sons = createPeople(parents.size(), true);

        return crossGenes(parents, sons);
    }

    private static List<People> crossGenes(List<People> parents, List<People> sons) {

        List<Integer> genesForSon = new ArrayList<>();
        Integer count = 0;

        for(People parent: parents) {
            genesForSon.addAll(parent.getGene().subList(0, 3));
            parent.setAllSons(sons);
        }

        for(People son: sons) {
            count++;

            if(count == 2) {
                Collections.reverse(genesForSon);
            }
            son.setGene(genesForSon);
        }

        return sons;
    }

    public static List<People> createPeople(Integer quantityPeopleInitial, Boolean isSons) {

        List<People> peopleList = new ArrayList<>();
        for (int i = 0; i < quantityPeopleInitial; i++) {
            peopleList.add(buildItemAndCreatePeople(isSons));
        }

        return peopleList;
    }

    private static People buildItemAndCreatePeople(Boolean buildGenesForSons) {

        People people = new People();

        Map<String, Map<Integer, Integer>> itemsBackPack = getItemForBackPack(new Random().nextInt(6));
        people.setItems(itemsBackPack);
        addNewItemOfList(people);

        people.setBackpackSize(getSizeOfBackPack(people));
        people.setName(String.valueOf(new Random().nextInt(99999999)));

        if (!buildGenesForSons) {
            people.setGene(createGenesOfPeople());
        }

        return people;
    }

    private static People addNewItemOfList(People people) {

        addNewItemOfList(people, 0, new HashMap<>());

        return people;
    }

    private static People addNewItemOfList(People people, Integer count, Map<String, Map<Integer, Integer>> newItems) {

        Map<Integer, Integer> valuesOfEntry = null;
        String keyOfEntry = null;

        for (Map.Entry<String, Map<Integer, Integer>> entry : people.getItems().entrySet()) {

            Map.Entry<Integer, Integer> values = entry.getValue().entrySet().iterator().next();

            keyOfEntry = entry.getKey();
            valuesOfEntry = entry.getValue();
            count += values.getKey();

        }

        if (count < people.getLimitBackpack()) {
            newItems.put(keyOfEntry, valuesOfEntry);
            people.setItems(getItemForBackPack(new Random().nextInt(6)));
            addNewItemOfList(people, count, newItems);
        }

        people.setItems(newItems);

        return people;
    }

    public static List<Integer> createGenesOfPeople() {

        int quantityGene = 0;
        List<Integer> genes = new ArrayList<>();

        while (quantityGene <= 5) {
            genes.add(new Random().nextInt(2));
            quantityGene++;
        }

        return genes;
    }

    public static Integer getSizeOfBackPack(People people) {

        Integer sizeOfBackPack = 0;

        for(Map.Entry<String, Map<Integer, Integer>> items: people.getItems().entrySet()) {
            Map.Entry<Integer, Integer> values = items.getValue().entrySet().iterator().next();
            sizeOfBackPack += values.getKey();
        }

        return sizeOfBackPack;
    }

    public static String itemBackPack(Integer keyItem) {

        String itemOfBackPack;

        switch (keyItem) {
            case 0:
                itemOfBackPack = "Saco de dormir";
                break;
            case 1:
                itemOfBackPack = "Corda";
                break;
            case 2:
                itemOfBackPack = "Canivete";
                break;
            case 3:
                itemOfBackPack = "Tocha";
                break;
            case 4:
                itemOfBackPack = "Garrafa";
                break;
            case 5:
                itemOfBackPack = "Comida";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + keyItem);
        }

        return itemOfBackPack;
    }

    public static Map<String, Map<Integer, Integer>> createItemsOfBackPack() {

        Map<String, Map<Integer, Integer>> items = new HashMap<>();

        items.put("Saco de dormir", Map.of(15, 15));
        items.put("Corda", Map.of(3, 7));
        items.put("Canivete", Map.of(2, 10));
        items.put("Tocha", Map.of(5, 5));
        items.put("Garrafa", Map.of(9, 8));
        items.put("Comida", Map.of(20, 17));

        return items;
    }

    public static Map<String, Map<Integer, Integer>> getItemForBackPack(Integer keyItemMap) {

        Map<String, Map<Integer, Integer>> buildNewMap = new HashMap<>();

        Map<String, Map<Integer, Integer>> getAllItemForBackPack = createItemsOfBackPack();
        String keyMap = itemBackPack(keyItemMap);
        buildNewMap.put(keyMap, getAllItemForBackPack.get(keyMap));

        return buildNewMap;

    }

}
