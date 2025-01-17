package language.features;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/** This covers features from Java 8 through 17. Focuses on high level ideas and snippets. */
public class Explorer {

  private static final Logger LOGGER = Logger.getLogger(Explorer.class.getName());

  public static void main(String[] args) throws Exception {

    java17();
    java16();
    java15();
    java14();
    java13();
    java12();
    java11();
    java10();
    java9();
    java8();

    System.exit(0);
  }

  /**
   * Features added in Java 8:
   *
   * <ul>
   *   <li>Streams
   *   <li>Lambdas
   *   <li>Changes to Interfaces & Method References
   *   <li>New Date & Time APIs
   * </ul>
   */
  private static void java8() throws Exception {
    // Features added in Java 8

    streams(8);
    lambdas(8);
    interfaces(8);
    date_time(8);

    // System.exit(0);
  }

  /**
   * Features added in Java 9:
   *
   * <ul>
   *   <li>Factory Methods for Collections
   *   <li>Stream API improvements
   *   <li>Optional Class Enhancements
   *   <li>Private interface methods
   *   <li>Java 9 - Language Modifications
   * </ul>
   */
  private static void java9() throws Exception {
    // Features added in Java 9

    collections(9);
    streams(9);
    optional(9);
    interfaces(9);
    java9_language_modifications();

    // System.exit(0);
  }

  /**
   * Features added in Java 10:
   *
   * <ul>
   *   <li>Local Variable Type Inference
   *   <li>Unmodifiable Collections
   *   <li>Optional*.orElseThrow()
   * </ul>
   */
  private static void java10() throws Exception {
    // Features added in Java 10

    var(10);
    collections(10);
    optional(10);

    // System.exit(0);
  }

  /**
   * Features added in Java 11:
   *
   * <ul>
   *   <li>HTTP Client
   *   <li>Local-Variable Syntax for Lambda Parameters
   *   <li>New String Methods
   *   <li>New File Methods
   *   <li>Collection to an Array
   *   <li>The Not Predicate Method
   * </ul>
   */
  private static void java11() throws Exception {
    // Features added in Java 11

    http_client(11);
    var(11);
    string(11);
    file(11);
    collections(11);
    not_predicate(11);

    // System.exit(0);
  }

  /**
   * Features added in Java 12:
   *
   * <ul>
   *   <li>String Class New Methods
   *   <li>File::mismatch Method
   *   <li>Teeing Collector
   *   <li>Compact Number Formatting
   * </ul>
   */
  private static void java12() throws Exception {
    // Features added in Java 12

    string(12);
    file(12);
    teeing(12);
    number(12);

    // System.exit(0);
  }

  /**
   * Features added in Java 13:
   *
   * <ul>
   *   <li>Switch Expressions
   * </ul>
   */
  private static void java13() throws Exception {
    // Features added in Java 13

    switch_expression(13);

    // System.exit(0);
  }

  /**
   * Features added in Java 14:
   *
   * <ul>
   *   <li>Records
   *   <li>Helpful NullPointerExceptions
   * </ul>
   */
  private static void java14() throws Exception {
    // Features added in Java 14

    records(14);
    npe(14);

    // System.exit(0);
  }

  /**
   * Features added in Java 15:
   *
   * <ul>
   *   <li>Text Blocks
   * </ul>
   */
  private static void java15() throws Exception {
    // Features added in Java 15

    text_blocks(15);

    // System.exit(0);
  }

  /**
   * Features added in Java 16:
   *
   * <ul>
   *   <li>Day Period Support
   *   <li>Add Stream.toList Method
   *   <li>Records
   *   <li>Pattern Matching for instanceof
   * </ul>
   */
  private static void java16() throws Exception {
    // Features added in Java 16

    date_time(16);
    streams(16);
    records(16);
    instance_of(16);

    // System.exit(0);
  }

  /**
   * Features added in Java 17:
   *
   * <ul>
   *   <li>Enhanced Pseudo-Random Number Generators
   *   <li>Sealed Classes
   * </ul>
   */
  private static void java17() throws Exception {
    // Features added in Java 17

    number(17);
    classes(17);

    // System.exit(0);
  }

  // =========== Language Changes=================================================================
  private static void classes(final int java) throws Exception {
    if(java == 17) {
      LOGGER.info("""
              Java 17 :: Sealed Classes. Rules:
              - All permitted subclasses must belong to the same module as the sealed class.
              - Every permitted subclass must explicitly extend the sealed class.
              - Every permitted subclass must define a modifier: final, sealed, or non-sealed.""");
      {
        System.out.println("Valid Example :: ");
        System.out.println("To seal an interface, we can apply the sealed modifier to its declaration. " +
                "The permits clause then specifies the classes that are permitted to implement the sealed interface:");
        System.out.println("""
                public sealed interface Service permits Car, Truck {
                
                    int getMaxServiceIntervalInMonths();
                
                    default int getMaxDistanceBetweenServicesInKilometers() {
                        return 100000;
                    }
                
                }
                """);
        System.out.println();
        System.out.println("Similar to interfaces, we can seal classes by applying the same sealed modifier. " +
                "The permits clause should be defined after any extends or implements clause");
        System.out.println("""
                public abstract sealed class Vehicle permits Car, Truck {
                
                    protected final String registrationNumber;
                
                    public Vehicle(String registrationNumber) {
                        this.registrationNumber = registrationNumber;
                    }
                
                    public String getRegistrationNumber() {
                        return registrationNumber;
                    }
                
                }
                """);
        System.out.println();
        System.out.println("A permitted subclass must define a modifier. " +
                "It may be declared final to prevent any further extensions:");
        System.out.println("""
                public final class Truck extends Vehicle implements Service {
                
                    private final int loadCapacity;
                
                    public Truck(int loadCapacity, String registrationNumber) {
                        super(registrationNumber);
                        this.loadCapacity = loadCapacity;
                    }
                
                    public int getLoadCapacity() {
                        return loadCapacity;
                    }
                
                    @Override
                    public int getMaxServiceIntervalInMonths() {
                        return 18;
                    }
                
                }
                """);
        System.out.println();
        System.out.println("Sealed classes work very well with records. Since records are implicitly final, " +
                "the sealed hierarchy is even more concise.");
        System.out.println("""
                public record Car(int numberOfSeats, String registrationNumber) implements Vehicle {
                
                    @Override
                    public String getRegistrationNumber() {
                        return registrationNumber;
                    }
                
                    public int getNumberOfSeats() {
                        return numberOfSeats;
                    }
                
                }
                """);

        delayBuffer();
      }
    }
  }

  private static void collections(final int java) throws Exception {
    if (java == 9) {
      LOGGER.info("Java 9 :: Collection Factory Methods");
      {
        Set<Integer> nums = Set.of(1, 2, 3);
        List<String> strings = List.of("one", "two", "three");

        System.out.println("nums = " + nums);
        System.out.println("strings = " + strings);
        delayBuffer();
      }
    } else if (java == 10) {
      LOGGER.info("Java 10 :: Unmodifiable Collections");
      {
        List<Integer> nums = List.of(1, 2, 3);

        // unmodifiable copy of the given Collection
        List<Integer> unmodifiable = List.copyOf(nums);
        try {
          unmodifiable.add(4);
        } catch (final UnsupportedOperationException e) {
          LOGGER.info("Cannot modify an immutable object. It is an UnsupportedOperation");
        }

        // Collect a Stream into unmodifiable List, Map or Set
        List<Integer> evens =
            nums.stream().filter(i -> i % 2 == 0).collect(Collectors.toUnmodifiableList());

        System.out.println("evenList.size() = " + evens.size());
        delayBuffer();

        try {
          evens.add(4);
        } catch (final UnsupportedOperationException e) {
          LOGGER.info("Cannot modify an immutable object. It is an UnsupportedOperation");
        }

        delayBuffer();
      }
    } else if (java == 11) {
        LOGGER.info("Java 11 :: Collection to an Array");
        {
            List<String> strings = List.of("one", "two");
            String[] listToArray = strings.toArray(String[]::new);
            System.out.println("listToArray.length = " + listToArray.length);

            delayBuffer();
        }
    }
  }

  private static void date_time(final int java) throws Exception {
    if (java == 8) {
      LOGGER.info(
          "Java 8 :: All java.time objects are immutable. "
              + "An Instant is a point on the time line. "
              + "A Duration is the difference between two instants. "
              + "LocalDateTime has no time zone information. "
              + "ZonedDateTime is a point in time in a given time zone. "
              + "Other important classes are Temporal Adjuster, Period & DateTimeFormatter. "
              + "All of these have methods around plus, minus and other common helpers");
      delayBuffer();

      LOGGER.info("Java 8 :: Instant & Duration");
      {
        Instant start = Instant.now();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5L));
        Instant end = Instant.now();

        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Elapsed ms :: " + timeElapsed.toMillis());

        Duration shouldBeLessThanOne = timeElapsed.minusSeconds(5);

        System.out.println("shouldBeLessThanOne = " + shouldBeLessThanOne);
        delayBuffer();
      }

      LOGGER.info("Java 8 :: Local APIs");
      {
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);

        LocalDate myBirthday = LocalDate.of(1993, 11, 15);
        System.out.println("myBirthday = " + myBirthday);

        LocalDate myBirthdayUsingMonthEnum = LocalDate.of(1993, Month.NOVEMBER, 15);
        System.out.println("myBirthdayUsingMonthEnum = " + myBirthdayUsingMonthEnum);

        long age = myBirthday.until(today, ChronoUnit.YEARS);
        System.out.println("age = " + age);

        LocalTime rightNow = LocalTime.now();
        System.out.println("rightNow = " + rightNow);

        LocalTime secretTime = LocalTime.of(23, 39);
        System.out.println("secretTime = " + secretTime);

        LocalTime tenHoursAfterSecret = secretTime.plus(10, ChronoUnit.HOURS);
        System.out.println("tenHoursAfterSecret = " + tenHoursAfterSecret);

        // compute specific dates, in this example lets see next workday from now
        TemporalAdjuster NEXT_WORKDAY =
            w -> {
              LocalDate result = (LocalDate) w;
              do {
                result = result.plusDays(1);
              } while (result.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue());
              return result;
            };
        LocalDate backToWork = today.with(NEXT_WORKDAY);
        System.out.println("backToWork = " + backToWork);

        // finding first Sunday after India won the 1983 World Cup
        LocalDate firstSundayAfter83Win =
            LocalDate.of(1983, Month.JUNE, 25).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println("firstSundayAfter83Win = " + firstSundayAfter83Win);

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Zoned APIs");
      {
        ZonedDateTime indiaWins2011WorldCup =
            ZonedDateTime.of(2011, 4, 2, 23, 31, 0, 0, ZoneId.of("Asia/Kolkata"));
        System.out.println("indiaWins2011WorldCup = " + indiaWins2011WorldCup);

        // Use Period when adjusting across time boundaries (takes into account the DST)
        // Duration won't work with Daylight Savings
        ZonedDateTime oneWeekLater = indiaWins2011WorldCup.plus(Period.ofDays(7));
        System.out.println("oneWeekLater = " + oneWeekLater);

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Formatting");
      {
        ZonedDateTime indiaWins2011WorldCup =
            ZonedDateTime.of(2011, 4, 2, 23, 31, 0, 0, ZoneId.of("Asia/Kolkata"));

        String formatted = DateTimeFormatter.ISO_DATE_TIME.format(indiaWins2011WorldCup);
        System.out.println("formatted using ISO = " + formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(indiaWins2011WorldCup);
        System.out.println("formatted using Long Localized Style = " + formatted);

        formatted = formatter.withLocale(Locale.UK).format(indiaWins2011WorldCup);
        System.out.println("formatter with Locale UK = " + formatted);
        delayBuffer();
      }
    } else if (java == 16) {
      LOGGER.info("Java 16 :: Day Period Support. Addition of new symbol 'B'");
      {
        LocalTime date = LocalTime.parse("10:21:13");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h B");
        System.out.println("date.format(formatter) = " + date.format(formatter));

        delayBuffer();
      }
    }
  }

  private static void file(final int java) throws Exception {
    if(java == 11) {
      LOGGER.info("Java 11 :: Reading & Writing of String to Files");
      final Path path = Files.writeString(Files.createTempFile("temp", ".txt"), "You are being watched!");
      System.out.println(Files.readString(path));
    } else if(java == 12) {
      LOGGER.info("Java 12 :: File Mismatch - used to find the position of first mismatched byte");

      Path tempF1 = Files.createTempFile("f1", ".txt");
      Path tempF2 = Files.createTempFile("f2", ".txt");

      Files.writeString(tempF1, "Hello World!");
      Files.writeString(tempF2, "Hello General!");

      System.out.println("Files.mismatch = "+Files.mismatch(tempF1, tempF2));

      delayBuffer();
    }
  }

  private static void http_client(final int java) throws Exception {
      if(java == 11) {
          LOGGER.info("""
                  Java 11 :: A new HTTP Client that implements HTTP/2 and WebSocketIt is aimed at replacing the
                  legacy HttpURLConnection. The new API is feature rich and is fully asynchronous. It improves
                  performance by using stream multiplexing, header compression, etc. The core classes/interfaces are:
                  - The HttpClient class, java.net.http.HttpClient
                  - The HttpRequest class, java.net.http.HttpRequest
                  - The HttpResponse<T> interface, java.net.http.HttpResponse
                  - The WebSocket interface, java.net.http.WebSocket""");
          {
              HttpClient client = HttpClient.newHttpClient();

              HttpRequest request = HttpRequest.newBuilder(new URI("https://postman-echo.com/get"))
                              .GET()
                              .version(HttpClient.Version.HTTP_2)
                              .timeout(Duration.of(10, ChronoUnit.SECONDS))
                              .build();

              // sync call
              HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
              System.out.println("response = " + response.body());

              // async call
              CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
              String result = future.thenApply(HttpResponse::body).get(10, TimeUnit.SECONDS);
              System.out.println("response = " + result);

              delayBuffer();
          }
      }
  }

  private static void instance_of(final int java) throws Exception {
    if(java == 16) {
      LOGGER.info("Java 16 :: Pattern Matching for instanceOf");
      {
        Object javaVersion = "Java 16";

        // before java 14
        if (javaVersion instanceof String) {
          String str = (String) javaVersion;
          str.strip();
        }

        // now
        if (javaVersion instanceof String s) {
          s.strip();
        }

        delayBuffer();
      }
    }
  }

  private static void interfaces(final int java) throws Exception {
    if (java == 8) {
      LOGGER.info("Java 8 :: Functional Interfaces");
      {
        // A Functional Interface is an interface with a single abstract method
        String[] words = new String[] {"commerce", "chain", "shame", "clerk", "horse"};
        Arrays.sort(
            words,
            new Comparator<String>() {
              @Override
              public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
              }
            });

        // can be replaced by
        Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
      }

      LOGGER.info("Java 8 :: Changes to Interfaces");
      {
        // Interfaces can now have concrete implementations called 'default methods'
        // This was done to safely and passively add new functionality to existing interfaces

        interface SimpleInterface {
          String getMessage();

          default int getAnswerToLife() {
            return 42;
          }
        }

        class Impl implements SimpleInterface {
          @Override
          public String getMessage() {
            return "Did not need to implement default getAnswerToLife";
          }
        }

        Impl impl = new Impl();
        System.out.println("impl = " + impl.getAnswerToLife());

        class ImplOverrider implements SimpleInterface {
          @Override
          public String getMessage() {
            return "I can override default methods";
          }

          @Override
          public int getAnswerToLife() {
            return 183;
          }
        }

        ImplOverrider overrider = new ImplOverrider();
        System.out.println("overrider = " + overrider.getAnswerToLife());

        // Interfaces can now have static methods too
        // Overall, Interfaces can now have default & static methods

        delayBuffer();
      }
    } else if (java == 9) {

      LOGGER.info("Java 9 :: Private Interface Methods");
      {
        // If there are multiple default methods which need to call similar functionality
        // We can now add private methods with behavior

        interface Java9Private {

          default void default1() {
            start();
          }

          default void default2() {
            start();
          }

          // not part of the public API
          private void start() {
            System.out.println("Starting...");
          }
        }

        class Impl implements Java9Private {}

        Impl impl = new Impl();
        impl.default1();
        impl.default2();

        delayBuffer();
      }
    }
  }

  private static void java9_language_modifications() throws Exception {
    LOGGER.info("These are small language improvements added in Java 9");
    {
      // try-with-resources
      BufferedReader bufferedReader =
          new BufferedReader(new FileReader(File.createTempFile("temp", ".txt")));

      try (bufferedReader) {
        System.out.println("Can Use Final or Effectively Final in Try with Resources");
      } finally {
        System.out.println("In finally block");
      }

      // diamond operator extension
      abstract class DemoClass<T> {
        abstract T add(T x, T y);
      }

      DemoClass<Integer> summer =
          new DemoClass<>() {
            Integer add(Integer x, Integer y) {
              return x + y;
            }
          };
      Integer sum = summer.add(13, 42);
      System.out.println("sum = " + sum);
    }

    delayBuffer();
  }

  private static void lambdas(final int java) throws Exception {
    if (java == 8) {
      LOGGER.info(
          "Java 8 :: The addition of functional programming constructs to its object-oriented root. "
              + "A “lambda expression” is a block of code that you can pass around so it can be executed "
              + "later, once or multiple times. You can supply a lambda expression whenever an object of an "
              + "interface with a single abstract method is expected (functional interface)");
      delayBuffer();

      LOGGER.info("Java 8 :: Lambda Expression Syntax");
      {
        // The arrow operator '->' followed by an 'expression'
        Comparator<Integer> comparator =
            (i1, i2) -> {
              if (i1 > i2) {
                return 1;
              } else if (i2 > i1) {
                return -1;
              }
              return 0;
            };
        System.out.println("Integer Comparison :: " + comparator.compare(1, 2));

        // creating a thread using lambda expression
        final Thread A =
            new Thread(() -> System.out.println("I was printed in SimpleThread::run()"));
        A.start();
        A.join();

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Method References");
      {
        // Lambda Expressions can sometimes be swapped with method references
        String[] words = new String[] {"commerce", "chain", "shame", "clerk", "horse"};
        System.out.println("words = " + Arrays.toString(words));

        Arrays.sort(words, (o1, o2) -> o1.compareToIgnoreCase(o2));
        // can be swapped with
        // we are essentially passing the compareToIgnoreCase method to the sort method
        Arrays.sort(words, String::compareToIgnoreCase);

        System.out.println("words = " + Arrays.toString(words));
        // A Lambda expression 'x -> System.out.println(x)' can be simplified as:
        // 'System.out::println'

        // At the end of the day the reason you'd use Lambdas is to 'defer execution'
        delayBuffer();
      }
    }
  }

  private static void not_predicate(final int java) throws Exception {
      if(java == 11) {
          LOGGER.info("Java 11 :: The Not Predicated");
          {
              List<String> strings = Arrays.asList(" ", "  ", "Hello");
              List<String> withoutEmptyStrings = strings.stream()
                      .filter(Predicate.not(String::isEmpty))
                      .collect(Collectors.toList());
              System.out.println("withoutEmptyStrings = " + withoutEmptyStrings);
              delayBuffer();
          }
      }
  }

  private static void npe(final int java) throws Exception {
    if(java == 14) {
      LOGGER.info("Java 14 :: Improved NPEs");
      {
        int[] arr = null;
        try {
          arr[0] = 1;
        } catch (final NullPointerException e) {
          LOGGER.info(e.getMessage());
        }
      }
    }
  }

  private static void number(final int java) throws Exception{
    if(java == 12) {
      LOGGER.info("Java 12 :: Compact Number Formatting");
      {
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.LONG);
        longFormat.setMaximumFractionDigits(2);

        System.out.println("shortFormat = " + longFormat.format(1313));

        delayBuffer();
      }
    } else if (java == 17) {
      LOGGER.info("Java 17 :: Pseudo-Random Number Generators");
      {

        System.out.println("All possible Generator IMPLs:");

        RandomGeneratorFactory.all()
                .map(fac -> fac.group()+ " : " +fac.name())
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        RandomGeneratorFactory.of("Xoshiro256PlusPlus").create()
                .ints(5, 0, 100)
                .forEach(System.out::println);

        delayBuffer();
      }
    }
  }

  private static void optional(final int java) throws Exception {
    if (java == 9) {
      LOGGER.info("Java 9 :: Improvements");
      {
        Optional<String> value = Optional.empty();

        // or()
        Optional<String> defaultValue = Optional.of("DEFAULT");
        Optional<String> result = value.or(() -> defaultValue);
        System.out.println("result = " + result);

        // ifPresentOrElse()
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        // when
        value.ifPresentOrElse(
            v -> successCounter.incrementAndGet(), onEmptyOptionalCounter::incrementAndGet);

        System.out.println("successCounter = " + successCounter);
        System.out.println("onEmptyOptionalCounter = " + onEmptyOptionalCounter);

        delayBuffer();
      }
    } else if (java == 10) {
      LOGGER.info("Java 10 :: orElseThrow");
      List<Integer> odds = List.of(1, 3, 5);
      try {
        odds.stream().filter(i -> i % 2 == 0).findFirst().orElseThrow();
      } catch (final NoSuchElementException e) {
        LOGGER.info("Didn't find any value from teh Optional. It is a NoSuchElementException");
      }
    }
  }

  private static void records(final int java) throws Exception {
    if(java == 14) {
      LOGGER.info("Java 14 :: Records were introduced to reduce repetitive boilerplate code in data model POJOs");

      record Java(String version, LocalDate releaseDate) {
        public Java {
          if(releaseDate.isBefore(LocalDate.of(1996, 1, 23))) {
            throw new IllegalArgumentException("Java was first release on 23rd Jan '96");
          }
        }
      };

      Java java1 = new Java("JDK 1.0 (Oak)", LocalDate.of(1996, 1, 23));

      System.out.println("java1.version = " + java1.version);
      System.out.println("java1.releaseDate = " + java1.releaseDate);

      delayBuffer();
    } else if (java == 16) {
      LOGGER.info("Java 16 :: Incremental Changes to Records");
      {
        record Java(String version, LocalDate releaseDate) {}

        // defining records as class members of inner classes wasn't possible earlier
        class Outer {
           class Inner {
            Java java2 = new Java("J2SE 1.2 (Playground)", LocalDate.of(1998, 12, 13));
          }
        }

        delayBuffer();
      }
    }
  }

  private static void streams(final int java) throws Exception {
    if (java == 8) {
      LOGGER.info(
          "Java 8 :: Streams are abstract constructs used for processing sequence of elements. It allows you to "
              + "perform aggregate operations, use intermediary pipelines, introduce parallelism, etc. "
              + "You can connect streams to each other following principles of the Decorator Pattern");
      delayBuffer();

      LOGGER.info("Java 8 :: Creation of Streams");
      {
        // Basics
        Stream<String> streamUsingOf = Stream.of("Lorem", "Ipsum");
        System.out.println(streamUsingOf.findFirst());

        Stream<Integer> emptyStream = Stream.empty();
        System.out.println(emptyStream.findFirst());

        // Infinite Streams

        // Infinite Stream of a constant using Stream.generate
        Stream<String> infiniteLorems = Stream.generate(() -> "Lorem");
        System.out.println(infiniteLorems.findAny());

        // infinite random numbers
        Stream<Integer> infiniteRandomNumbers =
            Stream.generate(() -> ThreadLocalRandom.current().nextInt());
        System.out.println(infiniteRandomNumbers.findAny());

        LOGGER.info(
            "Stream operations are not executed on the elements in the order in"
                + "which they are invoked on the streams. Nothing happens until a "
                + "terminal operation is called on the stream. Once that a terminal operation"
                + "asks for something, the stream lazily loads the first element and kicks off everything");

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Iteration -> Stream");
      {
        final URL resource = Explorer.class.getClassLoader().getResource("lorem_ipsum.txt");
        assert resource != null;
        final String loremIpsum = new String(Files.readAllBytes(Paths.get(resource.toURI())));
        List<String> words = asList(loremIpsum.split("[\\P{L}]+"));

        long count = 0;

        // using arrays
        for (String w : words) {
          if (w.length() > 8) count++;
        }
        System.out.println("count from foreach = " + count);

        // using streams, filter method to do the operation
        count =
            words.stream() // creation of a stream
                .filter(w -> w.length() > 5) // one or more intermediate operations
                .count(); // terminal operation which forces the lazy execution
        System.out.println("count from stream = " + count);

        // using parallel streams
        count = words.parallelStream().filter(w -> w.length() > 3).count();
        System.out.println("count from parallel stream = " + count);

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Interesting Operations on Streams");
      {
        // distinct, count
        int[] randomRepeatedNumbers = new int[] {873, 348, 210, 873, 348};
        System.out.print("Distinct Values :: ");
        Arrays.stream(randomRepeatedNumbers)
            .distinct()
            .forEach(
                value -> {
                  System.out.print(value + "\t");
                });
        System.out.println(
            "\n# of distinct numbers :: "
                + Arrays.stream(randomRepeatedNumbers).distinct().count());

        // sort
        System.out.print("Sorted Distinct Values :: ");
        Arrays.stream(randomRepeatedNumbers)
            .sorted()
            .distinct()
            .forEach(value -> System.out.print(value + "\t"));
        // reduction using a starter value and an accumulator function
        Stream<Integer> aStream = Stream.of(1, 2, 3);
        System.out.println(
            "\nReduction of 5 and sum of all in the stream :: "
                + aStream.reduce(5, (a, b) -> a + b));

        // matching using predicates
        List<String> randomStrings =
            asList(
                "3Fm9",
                "2S5",
                "aWC",
                "v5s69qD",
                "jZBXu",
                "MvzDT5r9",
                "ifBoLzc",
                "QOP6",
                "Pl0l76H",
                "r9Yz3q");
        boolean doYouHaveLetter_v = randomStrings.stream().anyMatch(s -> s.contains("v"));
        boolean doNoneOfYouHaveLetter_a = randomStrings.stream().noneMatch(s -> s.contains("a"));

        System.out.println("doYouHaveLetter_v = " + doYouHaveLetter_v);
        System.out.println("doNoneOfYouHaveLetter_a = " + doNoneOfYouHaveLetter_a);

        List<String> randomWords = asList("nurse", "measure", "prevent", "lunch", "degree");

        // use stream.map to transform using a function which applies to each value
        List<String> randomWordsUpperCased =
            randomWords.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Get them Upper-cased :: " + randomWordsUpperCased);

        // largest in a dictionary
        Optional<String> largest = randomWords.stream().max(String::compareToIgnoreCase);
        System.out.println("largest = " + largest.get());

        // use stream.flatMap to flatten a stream of streams to a single stream
        List<List<String>> nested =
            asList(
                asList("1::0", "1::1"),
                asList("2::0", "2::1", "2::2"),
                asList("3::0", "3::1", "3::2", "3::3"));
        List<String> flattened =
            nested.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("flattened = " + flattened);

        // infinite numbers in order using Stream.iterate
        Stream<BigInteger> infiniteNumbers =
            Stream.iterate(BigInteger.ZERO, i -> i.add(BigInteger.ONE));

        // Getting sub-stream using limit & skip
        infiniteNumbers.skip(5).limit(5).forEach(bigInt -> System.out.print(bigInt + "\t"));

        // Summarizing Stats

        delayBuffer();
      }

      LOGGER.info("Java 8 :: Collectors");
      {
        SimpleClass a = new SimpleClass(1, "One");
        SimpleClass b = new SimpleClass(2, "Two");

        // collecting into specific type of set
        List<Integer> numbers = asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        TreeSet<Integer> set = numbers.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("set = " + set);

        // collect a stream of string by joining them
        List<String> randomWords = asList("nurse", "measure", "prevent", "lunch", "degree");
        String result = randomWords.stream().collect(Collectors.joining(", "));
        System.out.println("result = " + result);

        // collect a stream of simple classes to a map
        Map<Integer, String> numToWords =
            Stream.of(a, b).collect(Collectors.toMap(SimpleClass::getA, SimpleClass::getB));
        System.out.println("numToWords = " + numToWords);

        IntSummaryStatistics statistics =
            randomWords.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println("statistics = " + statistics);

        delayBuffer();
      }
    } else if (java == 9) {
      LOGGER.info("Java 9 :: New Methods");
      {
        // new dropWhile method
        Stream<Integer> stream = Stream.of(4, 4, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list = stream.dropWhile(n -> (n / 4 == 1)).collect(Collectors.toList());
        System.out.println("list = " + list.size());

        // Predicates in iterate method
        IntStream.iterate(1, i -> i < 5, i -> i + 1).forEach(System.out::print);

        // Optionals can now be created into streams - this means it can be empty
        Stream<Integer> s = Optional.of(1).stream();

        delayBuffer();
      }
    } else if(java == 16) {
      LOGGER.info("Java 16 :: Stream.toList alternate to Collectors");
      {
        List<String> strings = List.of("one", "two", "three");
        List<String> usingToList = strings.stream().map(s -> s.substring(1)).toList();

        System.out.println("usingToList = " + usingToList);

        delayBuffer();
      }
    }
  }

  private static void string(final int java) throws Exception {
    if(java == 11) {
      LOGGER.info(" Java 11 :: New String Methods");
      {
        System.out.println("\" \".isBlank() = " + " ".isBlank());

        System.out.println();
        String multi = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                " Vestibulum a tortor at velit semper dapibus.\n" +
                "Fusce molestie ante diam, non laoreet nisi tempus eu.\n" +
                "Aliquam vel mi in eros bibendum venenatis sed in nisl.\n" +
                "Vestibulum sapien lectus, luctus aliquet tincidunt eget, cursus quis sapien.\n";
        System.out.println("Number of Lines = " + multi.lines().collect(Collectors.toList()).size());

        System.out.println();
        System.out.println("strip() is unicode aware, trim() wasn't");
        String random = "         Random        ";
        System.out.println("random.strip() = " + random.strip());
        System.out.println("random.stripLeading() = " + random.stripLeading());
        System.out.println("random.stripTrailing() = " + random.stripTrailing());

        System.out.println();
        String magic = "magic".repeat(5);
        System.out.println("magic x5 = " + magic);

        delayBuffer();
      }
    } else if(java == 12) {
      LOGGER.info(" Java 11 :: New String Methods");
      {
        String s = "Hello World!\nThis is Java 12.";
        s = s.indent(7);
        System.out.println("with 7 ident = " + s);

        delayBuffer();
      }
    }
  }

  private static void switch_expression(final int java) throws Exception{
    if(java == 13){
      LOGGER.info("Java 13 :: Switch Expressions");
      {
        String day = "FRIDAY";

        // Old Style
        boolean isTodayHoliday;
        switch (day) {
          case "MONDAY":
          case "TUESDAY":
          case "WEDNESDAY":
          case "THURSDAY":
          case "FRIDAY":
            isTodayHoliday = false;
            break;
          case "SATURDAY":
          case "SUNDAY":
            isTodayHoliday = true;
            break;
          default:
            System.out.println("I don't know this day=" + day);

         // new style with ->, break goes away, fall-through goes away
         isTodayHoliday = switch (day) {
           case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> false;
           case "SATURDAY", "SUNDAY" -> true;
           default -> throw new IllegalArgumentException("I don't know this day=" + day);
         };

         System.out.println("isTodayHoliday = " + isTodayHoliday);

         // There is also the yield keyword which can be used instead of break for returning values

         delayBuffer();
        }
      }
    }
  }

  private static void teeing(final int java) throws Exception {
    if(java == 12) {
      LOGGER.info("Java 12 :: Teeing Collector - It is a composite of two downstream collectors. " +
              "Every element is processed by both downstream collectors. Then their results are passed to the merge " +
              "function and transformed into the final result.");
      {
        double means = Stream.of(1, 2, 3, 5, 7, 11)
                             .collect(Collectors.teeing(
                                     Collectors.summingDouble(i -> i), //downstream 1
                                     Collectors.counting(), //downstream 2
                                     (sum, count) -> sum / count)); //merge function

        System.out.println("means = " + means);
        delayBuffer();
      }
    }
  }

  private static void text_blocks(final int java) throws Exception{
    if(java == 15) {
      LOGGER.info("Java 15 :: Text Blocks brings multi-line string support without line breaks");
      {
        String beforeJava15 = "<html>\n" +
                "   <body>\n" +
                "      <p>Hello, World</p>\n" +
                "   </body>\n" +
                "</html>\n";

        String java15 = """
                  <html>
                      <body>
                          <p>Hello, World</p>
                      </body>
                  </html>
                  """;

        System.out.println("java15 text blocks = " + java15);

        delayBuffer();
      }
    }
  }

  private static void var(final int java) throws Exception {
    if (java == 10) {
      LOGGER.info(
          "Java 10 :: Local Variable Type Interference. Can be used only in the following case:"
              + "1. Local Variable with initializer\n"
              + "2. Indexes of enhanced for loop or indexes\n"
              + "3. Local declared in for loop");
      {
        var message = "Hello there! General Kenobi!";
        System.out.println("message.getClass() = " + message.getClass());

        var didYouKnow = "var is not a keyword for passivity reasons, which means";

        System.out.println(didYouKnow + " var var = \"var\" is valid");
        var var = "var";

        // More valid ways to use var
        List<Integer> numbers = List.of(1, 2, 3);
        for (var number : numbers) {
          System.out.print(number + " ");
        }
        for (var i = 0; i < numbers.size(); i++) {
          System.out.print(numbers.get(i) + " ");
        }
        delayBuffer();
      }
    } else if(java == 11) {
        LOGGER.info("Java 11 :: Local-Variable Syntax for Lambda Params. " +
                "This allows us to use modifiers on local variables. Such as NonNull, etc.");
        {
            List<String> strings = Arrays.asList("one", "two");
            String result = strings.stream()
                    .map((@NonNull var x) -> x.toLowerCase())
                    .collect(Collectors.joining(", "));
            System.out.println("result = " + result);
        }

        delayBuffer();
    }
  }
  // =========== Utility =========================================================================

  private static void delayBuffer() throws InterruptedException {
    System.out.println();
    System.out.println();
    Thread.sleep(TimeUnit.SECONDS.toMillis(2L));
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class SimpleClass {
    int a;
    String b;
  }
}
