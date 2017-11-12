package cpt.blubber.example.roomrxjava2sample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by rueggeberg on 12.11.17.
 */

@Database(entities = ThingEntity.class, version = 1)
public abstract class ThingsDatabase extends RoomDatabase {
    public abstract ThingsDao thingsDao();
}
