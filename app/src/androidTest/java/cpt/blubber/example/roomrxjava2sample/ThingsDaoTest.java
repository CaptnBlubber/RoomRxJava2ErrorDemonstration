package cpt.blubber.example.roomrxjava2sample;

import android.arch.persistence.room.EmptyResultSetException;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ThingsDaoTest {

    private ThingsDatabase database;

    @Before
    public void setup() throws Exception {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), ThingsDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void testGetThingsSingleReturnsAllThings() throws Exception {
        //given we have 2 Things in the database
        database.thingsDao().insert(new ThingEntity("Foo"));
        database.thingsDao().insert(new ThingEntity("Bar"));

        //when querying all things
        database.thingsDao().getThingsSingle().test()
                //then the single should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and the size of the returned List should be 2
                .assertValue(actual -> actual.size() == 2);
    }

    @Test
    public void testGetThingsMaybeReturnsAllThings() throws Exception {
        //given we have 2 Things in the database
        database.thingsDao().insert(new ThingEntity("Foo"));
        database.thingsDao().insert(new ThingEntity("Bar"));

        //when querying all things
        database.thingsDao().getThingsMaybe().test()
                //then the maybe should be complete
                .assertComplete()
                //no errors should be present
                .assertNoErrors()
                //and the size of the returned List should be 2
                .assertValue(actual -> actual.size() == 2);
    }

    @Test
    public void testGetThingByIdSingleReturnsExistingThing() throws Exception {
        //given we have 2 Different Things in our database
        database.thingsDao().insert(new ThingEntity("Foo"));
        database.thingsDao().insert(new ThingEntity("Bar"));

        //when querying for thing with id 1
        database.thingsDao().getThingByIdSingle(1).test()
                //then the single should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and the name of the thing should be Foo
                .assertValue(actual -> actual.name.equals("Foo"));

        //when querying for thing with id 2
        database.thingsDao().getThingByIdSingle(2).test()
                //then the single should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and the name of the thing should be Bar
                .assertValue(actual -> actual.name.equals("Bar"));
    }

    @Test
    public void testGetThingByIdMaybeReturnsExistingThing() throws Exception {
        //given we have 2 Different Things in our database
        database.thingsDao().insert(new ThingEntity("Foo"));
        database.thingsDao().insert(new ThingEntity("Bar"));

        //when querying for thing with id 1
        database.thingsDao().getThingByIdMaybe(1).test()
                //then the maybe should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and the name of the thing should be Foo
                .assertValue(actual -> actual.name.equals("Foo"));

        //when querying for thing with id 2
        database.thingsDao().getThingByIdMaybe(2).test()
                //then the maybe should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and the name of the thing should be Bar
                .assertValue(actual -> actual.name.equals("Bar"));
    }

    @Test
    public void testGetThingByIdMaybeReturnsCompletesWithNoValuesForEmptyDatabase() throws Exception {
        //given we have an empty database

        //when querying for thing with id 1
        database.thingsDao().getThingByIdMaybe(1).test()
                //then the maybe should be complete
                .assertComplete()
                //no Errors should be present
                .assertNoErrors()
                //and there should be no values
                .assertNoValues();
    }

    @Test
    public void testGetThingByIdSingleThrowsEmptyResultSetExceptionForEmptyDatabase() throws Exception {
        //given we have an empty database

        //when querying for thing with id 1
        database.thingsDao().getThingByIdSingle(1).test()
                //then an EmptyResultSetException should be present
                .assertError(EmptyResultSetException.class);
    }

    @Test
    public void testGetThingsSingleThrowsEmptyResultSetExceptionForEmptyDatabase() throws Exception {
        //given we have an empty database

        //when querying for all things
        database.thingsDao().getThingsSingle().test()
                //then an EmptyResultSetException should be present
                .assertError(EmptyResultSetException.class);
    }

    @Test
    public void testGetThingsMaybeReturnsNoValuesForEmptyDatabase() throws Exception {
        //given we have an empty database

        //when querying for all things
        database.thingsDao().getThingsMaybe().test()
                //then the maybe should be complete
                .assertComplete()
                //no errors should be present
                .assertNoErrors()
                //and no values should be present
                .assertNoValues();
    }
}