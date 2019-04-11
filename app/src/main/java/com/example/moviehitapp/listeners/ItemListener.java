package com.example.moviehitapp.listeners;

/**It was used for listening to each item of recycler view , and executing some process
 * after clicking on each element.
 * But it doesn't work as well as it's needed.*/
public interface ItemListener<Item> {
    void onSelect(Item item);
}
