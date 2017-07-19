package com.han.seong.travelhelper.realm;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

import java.util.Date;

/**
 * Created by TEST on 2017-07-19.
 */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        //Access the Realm schema in order to create, modify or delete classes and their fields.
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 0){
            schema.create("Travel")
                    .addField("travelNo", int.class, FieldAttribute.PRIMARY_KEY)
                    .addField("title", String.class)
                    .addField("country", String.class)
                    .addField("startDate", Date.class)
                    .addField("endDate", Date.class)
                    .addField("image", String.class)
                    .addField("totalSpend", double.class)
                    .addField("totalBudget", double.class);
            oldVersion++;
        }
    }
}
