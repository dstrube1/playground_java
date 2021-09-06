Integer2String
================

```
public static String int2str(int number)
```
This function takes in an integer and returns that number in English as a String.

Using static method with private constructor because there is no need for an instance of this class. If this changes and an instance of the class is needed, this would be easy to change.

Using integers instead of long so the range of value inputs is between `Integer.MIN_VALUE` (-2,147,483,648) and `Integer.MAX_VALUE` (2,147,483,647). If a long range is preferred, this would require a little more work. (`Long.MAX_VALUE` = 9,223,372,036,854,775,807: a little over 9 quintillion)

If an even larger range is preferred, we would use BigInteger instead, but this would require even more time and effort.