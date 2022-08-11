package threepilar;
/*
An integer area is given, representing the area of some rectangle.

The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).

The goal is to find the minimal perimeter of any rectangle whose area equals area. The sides of this rectangle should be only integers.

For example, given integer area = 30, rectangles of area 30 are:

(1, 30), with a perimeter of 62,
(2, 15), with a perimeter of 34,
(3, 10), with a perimeter of 26,
(5, 6), with a perimeter of 22.
Write a function:

public static int minimunPerimeterRectangle(int area);

that, given an integer area, returns the minimal perimeter of any rectangle whose area is exactly equal to area.

For example, given an integer area = 30, the function should return 22, as explained above.

Write an efficient algorithm for the following assumptions:

area is an integer within the range [1..1,000,000,000].
*/
public class FindPerimeter {



        public static int minimunPerimeterRectangle(int area) {

            int minPerimeter = 1000000000;
            int sideA = 0;
            int sideB =0;
            int division = area;
            while(division > 0) {
                if(area % division == 0) {
                    sideA = division;
                    sideB = area / division;
                    if ((2*(sideA + sideB)) < minPerimeter) {
                        minPerimeter = 2*(sideA + sideB);
                    }
                }
                division--;
            }
            return minPerimeter;
        }

        public static String validateResult(int result, int expectedResult) {
            StringBuffer buffer = new StringBuffer();
            boolean validResult = (result == expectedResult);
            buffer.append("The result of the function was: ").append(result);
            buffer.append(" and the expected result is: ").append(expectedResult);
            buffer.append(" The test passes: ").append(validResult);
            return buffer.toString();
        }

        public static void main(String[] args) {
            System.out.println(validateResult(minimunPerimeterRectangle(10), 14));
            System.out.println(validateResult(minimunPerimeterRectangle(25), 20));
            System.out.println(validateResult(minimunPerimeterRectangle(30), 22));
            System.out.println(validateResult(minimunPerimeterRectangle(31), 64));
            System.out.println(validateResult(minimunPerimeterRectangle(40), 26));
            System.out.println(validateResult(minimunPerimeterRectangle(62), 66));
            System.out.println(validateResult(minimunPerimeterRectangle(100), 40));
            System.out.println(validateResult(minimunPerimeterRectangle(500), 90));
            System.out.println(validateResult(minimunPerimeterRectangle(1000), 130));
            System.out.println(validateResult(minimunPerimeterRectangle(2000), 180));
        }

}
