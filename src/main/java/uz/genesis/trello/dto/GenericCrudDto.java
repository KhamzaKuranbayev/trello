package uz.genesis.trello.dto;

import com.google.gson.Gson;

/**
 * Created by 'javokhir' on 04/07/2019
 */

public abstract class GenericCrudDto implements CrudDto {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
