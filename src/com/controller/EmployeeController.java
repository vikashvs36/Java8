package com.controller;

import com.dao.DepartmentDao;
import com.domain.Department;
import com.domain.Employee;
import com.service.EmployeeServiceImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Create Employee Controller.
public class EmployeeController {

    /*  *
        *
        * We will see here the most important method of the stream API with example one by one.
        *
        * -----------Method Detail------------------
        *
        * There are mainly two type of operation we will use. first one is Intermediate operation and the second one is Terminal operations.
        *
        *  >>>> Intermediate operation
        *
        * 1. Stream<T> filter(Predicate<? super T> predicate)
        *   Returns a stream consisting of the elements of this stream that match the given predicate.
        *
        * 2. <R> Stream<R> map(Function<? super T,? extends R> mapper)
        *   Returns a stream consisting of the results of applying the given function to the elements of this stream.
        *
        * 3. Stream<T> distinct()
        *   Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.
        *
        * 4. Stream<T> sorted()
        *   Returns a stream consisting of the elements of this stream, sorted according to natural order. If the elements of this stream are
        *   not Comparable, a java.lang.ClassCastException may be thrown when the terminal operation is executed.
        *
        * 5. Stream<T> sorted(Comparator<? super T> comparator)
        *   Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator. For ordered streams,
        *   the sort is stable. For unordered streams, no stability guarantees are made.
        *
        * 6. Stream<T> peek(Consumer<? super T> action)
        *   Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements
        *   are consumed from the resulting stream.
        *
        * 7. Stream<T> limit(long maxSize)
        *   Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        *
        * 8. Stream<T> skip(long n)
        *   Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream. If this
        *   stream contains fewer than n elements then an empty stream will be returned.
        *
        *
        *
        * -------->> Terminal operation.
        *
        * 9. void forEach(Consumer<? super T> action)
        *   Performs an action for each element of this stream.
        *
        * 10. void forEachOrdered(Consumer<? super T> action)
        *   Performs an action for each element of this stream, in the encounter order of the stream if the stream has a defined encounter
        *   order.
        *
        * 11. a). Object[] toArray()
        *   Returns an array containing the elements of this stream.
        *
        * 11. b). <A> A[] toArray(IntFunction<A[]> generator)
        *   Returns an array containing the elements of this stream, using the provided generator function to allocate the returned array, as well as
        *   any additional arrays that might be required for a partitioned execution or for resizing.
        *
        *   for example :- Person[] men = people.stream()
        *                       .filter(p -> p.getGender() == MALE)
        *                       .toArray(Person[]::new);
        *
        * 12. a) <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
        *   Performs a mutable reduction operation on the elements of this stream. A mutable reduction is one in which the reduced value is a mutable
        *   result container, such as an ArrayList, and elements are incorporated by updating the state of the result rather than by replacing the result.
        *   This produces a result equivalent to:
        *
        *   R result = supplier.get();
        *   for (T element : this stream){
        *       accumulator.accept(result, element);
        *    }
        *   return result;
        *
        * 12. b) <R,A> R collect(Collector<? super T,A,R> collector)
        *   Performs a mutable reduction operation on the elements of this stream using a Collector. A Collector encapsulates the functions used
        *   as arguments to collect(Supplier, BiConsumer, BiConsumer), allowing for reuse of collection strategies and composition of collect
        *   operations such as multiple-level grouping or partitioning.
        *
        *   for example :-
        *           a) The following will accumulate strings into an ArrayList:
        *               List<String> asList = stringStream.collect(Collectors.toList());
        *           b) The following will classify Person objects by city:
        *               Map<String, List<Person>> peopleByCity
        *                          = personStream.collect(Collectors.groupingBy(Person::getCity));
        *
        * 13. Optional<T> min(Comparator<? super T> comparator)
        *     Returns the minimum element of this stream according to the provided Comparator. This is a special case of a reduction.
        *
        * 14. Optional<T> max(Comparator<? super T> comparator)
        *      Returns the maximum element of this stream according to the provided Comparator. This is a special case of a reduction.
        *
        * 15. long count()
        *       Returns the count of elements in this stream. This is a special case of a reduction and is equivalent to:
        *
        *       return mapToLong(e -> 1L).sum();
        *
        * 16. boolean anyMatch(Predicate<? super T> predicate)
        *
        * 17. boolean allMatch(Predicate<? super T> predicate)
        *
        * 18. boolean noneMatch(Predicate<? super T> predicate)
        *
        * 19. Optional<T> findFirst()
        *
        * 20. Optional<T> findAny()
        *
        * 21. static <T> Stream<T> empty()
        *       Returns an empty sequential Stream.
        *
        * 22. static <T> Stream<T> of(T t)
        *       Returns a sequential Stream containing a single element.
        *
        * 23. static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
        *
        * 24. static <T> Stream<T> generate(Supplier<T> s)
        *       Returns an infinite sequential unordered stream where each element is generated by the provided Supplier.
        *       This is suitable for generating constant streams, streams of random elements, etc.
        *
        * 25. static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
        *
        *
        * All method details are taken from 'Java 8 Doc' for reference Url 'https://docs.oracle.com/javase/8/docs/api/'
        *
        * */



    // We can see example of all method which are given above. We have to use both each together Intermediate operation and Terminal operations. So, Lets start the session :-

    // Create Employee Service.
    static EmployeeServiceImpl employeeService=new EmployeeServiceImpl();
    static List<Employee> employeeList=employeeService.list();

    // Method 1> filter() and 9> forEach()
    public static void filterAndForEach(int age){

        // Print all employee whose age is greater than age=?.
        employeeList.stream()
                .filter(i-> i.getAge() > age)
                .forEach(System.out::println);      //  .forEach(i-> System.out.println(i));
    }

    //Method 10>. forEachOrdered(long n)
    public static void forEachOrdered(){

        // print the elements of stream in encounter order
        Stream.of("one", "two", "three", "four").forEachOrdered(System.out::println);
    }

    // Method 2> Map()
    public static void mapExample(double sal){

        // Print all only employees names.
        employeeList.stream()
                .map(i-> i.getName())
                .forEach(System.out::println);      //  .forEach(i-> System.out.println(i));
    }

    // Method 3> distinct()
    public static void distinct(){
        // Get distinct age
        employeeList.stream()
                .map(emp->emp.getAge())
                .distinct()
                .forEach(System.out::println);
    }

    // Method 4> sorted()
    public static void sorted1(){
        // Get salary in natural order
        employeeList.stream()
                .map(emp->emp.getSalary())
                .sorted()
                .forEach(System.out::println);
    }

    // Method 5> sorted()
    public static void sorted2(){
        // Get salary in accending order
        employeeList.stream()
                .map(emp->emp.getSalary())
                .sorted((sal1, sal2)-> sal1<sal2 ? -1 : sal1>sal2 ? 1 : 0)
                .forEach(System.out::println);
    }

    // Method 6> peek() 22> Stream<T> of(T t)
    public static void peek(){

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    // Method 7> limit() 22> Stream<T> of(T t)
    public static void limit(){

        //get only two element in limit
        Stream.of("one", "two", "three", "four")
                .limit(2)
                .forEach(i-> System.out.println(i));
    }

    //Method 8>. skip(long n)
    public static void skip(long n){

        // It will skip first to nth elements
        Stream.of("one", "two", "three", "four")
                .skip(2)
                .forEach(i-> System.out.println(i));
    }

    // Method 11> a) Object[] toArray()
    public static void toArray1(){

        Object[] stringList=  Stream.of("one", "two", "three", "four")
                .toArray();
        System.out.println(stringList.toString());
    }

    // Method 11> b). toArray(IntFunction<A[]> generator)
    public static void toArray2(){

        // It will skip first to nth elements
        Employee[] employees = employeeList.stream()
                .filter(emp -> emp.getAge() == 28)
                .toArray(Employee[]::new);

        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }

    // 12. a) <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
    public static void collect1(){

        // The following will take a stream of strings and concatenates them into a single string
        String names = employeeList.stream()
                .map(emp -> emp.getName())
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

            System.out.println(names);

        // the following will accumulate strings into an ArrayList:
        List<String> asList = employeeList.stream()
                .map(emp->emp.getName())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(asList);

    }

    // 12. b) <R,A> R collect(Collector<? super T,A,R> collector)
    public static void collect2(){

        // The following will accumulate strings into an ArrayList:
        List<Employee> asList = employeeList.stream().collect(Collectors.toList());
        for (Employee employee : asList) System.out.println(employee);

        // The following will classify Person objects by age:

        Map<Integer, List<Employee>> peopleByAge = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getAge));
        peopleByAge.forEach((k,v)-> System.out.println((k + ":" + v)));
    }

    // 13. Optional<T> min(Comparator<? super T> comparator)
    public static void min(){

        // Calculate min salary
        Double min=employeeList.stream()
                .map(emp->emp.getSalary())
                .min((i,j)->i.compareTo(j))
                .get();

        System.out.println(min);
    }

    // 14. Optional<T> max(Comparator<? super T> comparator)
    public static void max(){

        // Calculate max salary
        Double max=employeeList.stream()
                .map(emp->emp.getSalary())
                .max((i,j)->i.compareTo(j))
                .get();

        System.out.println(max);
    }

    // 15 long count()
    public static void count(){

        // calculate name count
        Long count=employeeList.stream()
                .map(emp->emp.getDepartment().getName())
                .count();
        System.out.println(count);
    }

    // 16. boolean anyMatch(Predicate<? super T> predicate)
    public static void anyMatch(){
        // find name
        boolean empName=employeeList.stream()
                .anyMatch(emp->emp.getName().equalsIgnoreCase("vikash"));

        System.out.println(empName);
    }

    // 17. boolean allMatch(Predicate<? super T> predicate)
    public static void allMatch(Integer a){
        // find age
        boolean age=employeeList.stream()
                .allMatch(emp->emp.getAge() == a);
        System.out.println(age);
    }

    // 18. boolean noneMatch(Predicate<? super T> predicate)
    public static void noneMatch(String name){
        // find has_name
        boolean hasName=employeeList.stream()
                .noneMatch(emp->emp.getName().equalsIgnoreCase(name));
        System.out.println(hasName);
    }

    // 19. Optional<T> findFirst()
    public static void findFirst(){
        //find first data
        Employee employee=employeeList.stream()
                .findFirst()
                .get();
        System.out.println(employee);
    }

    // 20. Optional<T> findAny()
    public static void findAny(){

        //find any
        Employee employee=employeeList.stream()
                .findAny()
                .get();
        System.out.println(employee);
    }

    // 21. static <T> Stream<T> empty()
    public static void empty(){
        // creating an empty IntStream, a sequence of
        // primitive int-valued elements
        IntStream stream = IntStream.empty();

        // Displaying an empty sequential stream
        System.out.println(stream.count());

    }

    // 23. static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
    public static void iterate(){

        // Calculate fibonnaci with iterate and Stream
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        Stream.iterate(2, (Integer n) -> n*n)
                .limit(5)
                .forEach(System.out::println);
    }

    // 24 static <T> Stream<T> generate(Supplier<T> s)
    public static void generate(){

        // generate 5 random number
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    // 25. static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
    public static void concat(){

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> s = Stream.concat(numbers.stream(),
                numbers1.stream());

        System.out.println(s);
        System.out.println("count : "+s.count());
    }

    // Find all Employee List object.
    public static Map<String, List<Employee>> list(){

        // Find all Employee according to Department using grouping from stream.
        return employeeService.list().stream()
                .collect(Collectors.groupingBy(
                        emp->emp.getDepartment().getName(),
                        Collectors.toList()));
    }

    // Create main() method to test method which is given above.
    /*public static void main(String[] args) {

        // Method 1> filter() and 9> forEach()
        filterAndForEach(18);

        // Method 1> filter() and 9> forEach()
        forEachOrdered();

        // Method 2> Map
        mapExample(25000);

        // Method 3> distinct Obj
        distinct();

        // Method 4> sorted()
        sorted1();

        // Method 5> sorted()
        sorted2();

        // Method 6> sorted()
        peek();

        // Method 7> limit()
        limit();

        // Method 8> skip()
        skip(2);

        // Method 11> Object[] toArray()
        toArray1();

        // Method 11> b). toArray(IntFunction<A[]> generator)
        toArray2();

        // Method 12> a). collect()
        collect1();

        // 12. b) <R,A> R collect(Collector<? super T,A,R> collector)
        collect2();

        // 13. Optional<T> min(Comparator<? super T> comparator)
        min();

        // 14 Calculate max salary
        max();

        // 15 count
        count();

        // 16. boolean anyMatch(Predicate<? super T> predicate)
        anyMatch();

        // 17. boolean allMatch(Predicate<? super T> predicate)
        allMatch(28);

        // 18. boolean noneMatch(Predicate<? super T> predicate)
        noneMatch("vikash");

        // 19. Optional<T> findFirst()
        findFirst();

        // 20. Optional<T> findAny()
        findAny();

        // 21. static <T> Stream<T> empty()
        empty();

        // 23. Calculate fibonnaci with iterate and Stream
        iterate();

        // 24. static <T> Stream<T> generate(Supplier<T> s)
        generate();

        // 25. static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
        concat();

        // Create map object containing Employee list.
        Map<String, List<Employee>> employees=list();
        // Iterate map object using forEach method.
        employees.forEach((k, v) -> System.out.println((k + ":" + v)));
    }*/

    public static void main(String[] args) {
        List<Department> departmentList=DepartmentDao.list();
        Collections.sort(departmentList);
        for (Department department : departmentList) {
            System.out.println(department.getName());
        }

    }


    /*
    * Some question to solve it.
    *
    * 1. Increase 10% salary if salary is less than 25000
    *
    * 2. Get distinct object. distinct value is like age.
    *
    * */

}
