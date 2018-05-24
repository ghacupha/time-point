
[![Build Status](https://travis-ci.org/ghacupha/time-point.svg?branch=master)](https://travis-ci.org/ghacupha/time-point)
[![](https://jitpack.io/v/ghacupha/time-point.svg)](https://jitpack.io/#ghacupha/time-point)

# time-point

A thin wrapper for localDate.

Using TimePoint interface time is hereby represented to the accuracy of one day. This is further used to implement ranges
that represent time in days. The same range can then be queried as to whether a TimePoint belongs or does not belong to a
given time range

###### Usage
See the below tests:

```java
private static TimePoint timePoint;

    @BeforeAll
    public static void setUp() throws Exception {
        timePoint = new SimpleDate(2018,5,12);
    }

    @Test
    public void addition() throws Exception {

        //assertEquals(timePoint.addDays(5),new SimpleDate(2018,5,17));
        assertTrue(timePoint.addDays(5).equals(new SimpleDate(2018,5,17)));
        assertTrue(timePoint.addDays(30).equals(new SimpleDate(2018,6,11)));

    }

    @Test
    public void minusDays() throws Exception {

        assertTrue(timePoint.minusDays(5).equals(new SimpleDate(2018,5,7)));
        assertTrue(timePoint.minusDays(30).equals(new SimpleDate(2018,4,12)));
    }

    @Test
    public void after() throws Exception {
        assertTrue(timePoint.after(new SimpleDate(2018,5,11)));
        assertTrue(timePoint.after(new SimpleDate(2018,3,31)));
    }

    @Test
    public void before() throws Exception {
        assertTrue(timePoint.before(new SimpleDate(2018,7,1)));
        assertTrue(timePoint.before(new SimpleDate(2018,5,13)));
    }

    @Test
    public void newTimePoint() throws Exception {
        TimePoint testPoint = new SimpleDate();

        assertTrue(new SimpleDate().after(testPoint.minusDays(1)));
        assertTrue(new SimpleDate().before(testPoint.addDays(1)));
    }

    @Test
    public void toStringTest() throws Exception {

        assertEquals("2018-05-08",new SimpleDate(2018,5,8).toString());
    }
```

Ok so that was the TimePoint wrapper in action, please note you java version 8 for this. Now this is the *DateRange*

```java
    // somewhere in the beginning
    import static io.github.ghacupha.time.point.SimpleDate.on;

    private static DateRange dateRange;

    @BeforeAll
    public static void setUp() throws Exception {
        dateRange = new DateRange(SimpleDate.newMoment(2017,9,30), SimpleDate.newMoment(2017,12,30));
    }

    @Test
    public void upTo() {

        assertTrue(dateRange.includes(on(2017,11,30)));
        assertTrue(dateRange.includes(on(2017,12,30)));
        assertFalse(dateRange.includes(on(2017,12,31)));

        DateRange infiniteStart = DateRange.upTo((SimpleDate) on(2017,11,30));
        DateRange infiniteEnd = DateRange.startingOn((SimpleDate) on(2017,11,30));

        assertTrue(infiniteStart.includes(on(1900,01,01)));
        assertTrue(infiniteEnd.includes(on(9999,01,01)));
    }
```

###### Supported operations in dateRange
So there. The following operations should work for SimpleDate (Timepoint Implementation)
- Addition
- Subtraction

The following operations are available for the *DateRange*
- upTo create infinite range upto a TimePoint
- startingOn creates infinite range starting at a given TimePoint
- combination combines date ranges into one continuous date range
- isContiguous checks is an array of date ranges can create continous timeline
- includes checks if a given range includes a given timePoint
- isEmpty checks if the dateRange is Empty
- overlaps checks if the dateRange in the argument overlaps this dateRange
- includes checks if the dateRange in the argument includes this dateRange
- gap checks if there exists a gap between the dateRange argument and this
- abuts checks if the dateRange starts just a day after the end of this range or begins
just a day after this dateRange
- partitionedBy checks if the dateRanges in the argument are separated by this dateRange

##### Application
An [abstraction](https://github.com/ghacupha/book-keeper) for maintaining accounting books uses the time-point and in
particaluar the *dateRange* abstraction to determine accounting entries that are effective as at a particular point in
time

#### Installation
You could download this with maven using jitpack repository like so:
```java
<repositories>
  <repository>
   <id>jitpack.io</id>
   <url>https://jitpack.io</url>
  </repository>
</repositories>

<!-- Dependencies -->
<dependency>
  <groupId>com.github.ghacupha</groupId>
  <artifactId>time-point</artifactId>
  <version>1.0.0</version>
</dependency>

```

###### To install from source
**Requirements**
 - Java 8. Seriously why would you be using version 6 in 2018?
 - Maven version 3.2.5
 - GIT, obviosly

 in your favourite work folder do this:
 ```
    git clone https://github.com/ghacupha/time-point.git

    cd time-point

    mvn clean package

    mvn install

    #Done
 ```

Then now you could add it from your local maven repository like so:
```java
<!-- Dependencies -->
<dependency>
  <groupId>com.github.ghacupha</groupId>
  <artifactId>cash</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### TODO
- Additional illustrative tests with the *DateRange* object

## Contact

Feedback and contributions are welcome.
Feel free to send an [email](mailto:mailnjeru@gmail.com) or submit a pull request.

## License

This code is free to use under the terms of the [LGPL v3 license](https://opensource.org/licenses/lgpl-3.0.html).
