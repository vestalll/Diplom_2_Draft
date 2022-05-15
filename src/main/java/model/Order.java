package model;

import java.util.List;

public class Order {

    List<String> ingredientHashes;

    public Order(List<String> ingredientHashes) {
        this.ingredientHashes = ingredientHashes;
    }

    public Order() {}

    public List<String> getIngredientHashes() {
        return ingredientHashes;
    }

    public void setIngredientHashes(List<String> ingredientHashes) {
        this.ingredientHashes = ingredientHashes;
    }


}
