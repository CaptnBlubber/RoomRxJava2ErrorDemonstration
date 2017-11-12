package cpt.blubber.example.roomrxjava2sample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rueggeberg on 12.11.17.
 */

@Entity(tableName = "things")
public class ThingEntity {
    public final String name;
    @PrimaryKey(autoGenerate = true)
    int id;

    public ThingEntity(String name) {
        this.name = name;
    }
}
