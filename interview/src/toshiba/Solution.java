package toshiba;


import java.util.*;
import java.util.stream.IntStream;
import java.util.concurrent.*;


// TODO
// 1)
// Fix CustomerData so that the output is sorted
// [{Aladin, Smith}, {Doe, Jane}, {Doe, John}, {doe,tom}, {Smith, Adam}]

// TODO
// 2)
// Implement the getCustomerByName() method to return the Customer
// object from customerData matching the first and last name provided to
// the method as input parameters.

// TODO
// 3)
// Update CustomerData's addCustomerEntry method, to make sure that duplicate customers are not added to the collection
//
// That is, the list is unchanged after multiple calls to
// addCustomer("John", "Doe");
// addCustomer("John", "Doe");
// addCustomer("John", "Doe");


// TODO
// 4)
// Modify the inner Customer class so that its name fields cannot be
// modified after object creation (a.k.a. immutability).


// TODO
// 5)
// Assume that a singleton instance of CustomerData is deployed in an
// application server container where multiple threads may access its
// methods concurrently. How can you ensure that all threads see the
// most up-to-date value of the customerData field?  Update the code
// to make CustomerData thread safe with minimal performance impact.


public class Solution {

    static class CustomerData {

        // The value or type of this collection may be changed
        List<Customer> _data = new ArrayList<>();

        /**
         * Return a sorted list of customers that have been added. Customers must be order by Lastname, and then First name. Doe, Jane then Doe, John for example
         */
        public synchronized  List<Customer> getCustomers() {
            // TODO: 1
            Collections.sort(_data, new FirstNameComparator());
            Collections.sort(_data, new LastNameComparator());

            return _data;
        }
        class LastNameComparator implements Comparator<Customer> {

            @Override
            public int compare(Customer cus1, Customer cus2) {
                return cus1.getLastName().compareTo(cus2.getLastName());
            }
        }
        class FirstNameComparator implements Comparator<Customer> {

            @Override
            public int compare(Customer cus1, Customer cus2) {
                return cus1.getFirstName().compareTo(cus2.getFirstName());
            }}
        /**
         * Return the  customer by the provided info
         */
        public synchronized  Customer getCustomerByName(String firstName, String lastName) {
            // TODO: 2
            Customer customer = null;
            for(Customer search: _data) {
                if(search.getLastName().equals(lastName)
                        && search.getFirstName().equals(firstName)) {
                    customer = search;
                }
            }
            return customer;
        }

        /**
         * Add a customer entry if it does not yet exist in the collection
         */
        public synchronized  void addCustomerEntry(Customer customer) {
            // TODO: 3
            Set<Customer> customerSet = new HashSet<>(_data);
            int customerSize = customerSet.size();
            customerSet.add(customer);

            // System.out.println( customerSize +" " + customerSet.size());
            if (customerSet.size() > customerSize) {

                _data.add(customer);
            }

        }


        public synchronized void clear()
        {
            _data.clear();
        }
    }


    // TODO: Task 4
    static class Customer {

        private String firstName;
        private String middleInitial;
        private String lastName;
        public String city;
        public String state;
        public String zipCode;
        public String phoneNumber;
        public Integer customerNumber;

        Customer() {
        }

        Customer(String fName, String lName) {
            this.firstName = fName;
            this.lastName = lName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        @Override
        public String toString() {
            return "{" + lastName + ", " + firstName + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(firstName, customer.firstName) &&
                    Objects.equals(lastName, customer.lastName);
        }

        @Override
        public int hashCode() {
            int result = 17;
            if (firstName != null) {
                result = 31 * result + firstName.hashCode();
            }
            if (lastName != null) {
                result = 31 * result + lastName.hashCode();
            }
            return result;

        }
    }

    /**
     * The customer data handler
     */
    static CustomerData customers = new CustomerData();

    /**
     * Add a customer to the data list
     *
     * @param firstname first name of the customer
     * @param lastname  last name of the customer
     */
    public static void addCustomer(String firstname, String lastname) {

        Customer customer = new Customer();
        customer.firstName = firstname;
        customer.lastName = lastname;
        customers.addCustomerEntry(customer);
    }


    /**
     * Add a customer to the data list
     *
     * @param name the name of the customer
     */
    public static void addCustomer(String name) {

        String[] split = name.split(" ", 2);
        Customer customer = new Customer();
        customer.firstName = split[0];
        customer.lastName = split[1];
        customers.addCustomerEntry(customer);
    }

    public static void main(String[] args) {

        addCustomer("John", "Doe");
        addCustomer("Jane Doe");
        addCustomer("Aladin Smith");
        addCustomer("Adam Smith");

        /*
            Verification tests. No need to modify any code below
         */

        System.out.println("\nTask 1");

        List<Customer> sortedCustomers = customers.getCustomers();

        List<Customer> expectedCustomers = new LinkedList<Customer>() {
            {
                addLast(new Customer("Jane", "Doe"));
                addLast(new Customer("John", "Doe"));
                addLast(new Customer("Adam", "Smith"));
                addLast(new Customer("Aladin", "Smith"));
            }
        };

        System.out.println(expectedCustomers.equals(sortedCustomers) ? "PASS" : "FAIL");

        System.out.println("\nTask 2");

        Customer tmp;
        tmp = customers.getCustomerByName("Adam", "Smith");
        System.out.println(tmp != null && tmp.firstName.equals("Adam") && tmp.lastName.equals("Smith") ? "PASS" : "FAIL");

        tmp = customers.getCustomerByName("John", "Doe");
        System.out.println(tmp != null && tmp.firstName.equals("John") && tmp.lastName.equals("Doe") ? "PASS" : "FAIL");

        tmp = customers.getCustomerByName("Other", "Doe");
        System.out.println(tmp == null ? "PASS" : "FAIL");

        tmp = customers.getCustomerByName("Agent", "Smith");
        System.out.println(tmp == null ? "PASS" : "FAIL");


        System.out.println("\nTask 3");

        addCustomer("Adam Smith");
        addCustomer("Adam Smith");
        addCustomer("Adam Smith");
        addCustomer("Adam Smith");

        sortedCustomers = customers.getCustomers();
        expectedCustomers = new LinkedList<Customer>() {
            {
                addLast(new Customer("Jane", "Doe"));
                addLast(new Customer("John", "Doe"));
                addLast(new Customer("Adam", "Smith"));
                addLast(new Customer("Aladin", "Smith"));
            }
        };

        System.out.println(expectedCustomers.equals(sortedCustomers) ? "PASS" : "FAIL");

        System.out.println("\nTask 4\nNo tests. Please, explain your solution.");

        System.out.println("Making the variables private .");
        System.out.println("Only allowing modifications at the creation of the object.");
        System.out.println("Creating getters.");

        System.out.println("\nTask 5");

        customers.clear();

        final Map<Integer,Integer> countSet = new ConcurrentHashMap<>();

        IntStream.range(1, 10000).parallel().forEach(t -> {
            Customer c = new Customer("FirstName_"+t, "LastName_"+t);
            c.customerNumber = t;
            customers.addCustomerEntry(c);
            countSet.put(t,t);
        });

        try
        {

            List<Customer> multithreadedCustomers = customers.getCustomers();
            for(Customer c : multithreadedCustomers)
            {
                countSet.remove(c.customerNumber);
            }


            System.out.println( countSet.size() <= 0 ? "PASS" : "FAIL - missingRecordsCount: " + countSet.size());
        } catch (Exception e )
        {
            System.out.println("FAIL - " + e.getMessage());
        }

    }


}
