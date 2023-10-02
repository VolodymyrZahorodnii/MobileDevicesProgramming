// 1. Написати функцію з одним аргументом, яка перевіряє чи є аргумент парним чи непарним. Функція повинна повернути "even" або "odd" в залежності від того яке то число. Якщо аргумент null то необхідно повернути пусту строку.//


// Але перед цим додам дві тестові функції:

fun assertEquals(res: Int, exp: Int, msg: String? = null) {
    assert(res == exp) {
        "Assertion failed: $msg\nExpected: $exp\nActual: $res"
    }
}

fun assertEquals(res: String, exp: String, msg: String? = null) {
    assert(res == exp) {
        "Assertion failed: $msg\nExpected: $exp\nActual: $res"
    }
}

fun isEven(arg: Int?): String {
    // Якщо аргумент null, то повертаємо пусту строку
    if (arg == null) {
        return ""
    }

    // Повертаємо "even" якщо аргумент парний, "odd" в іншому випадку
    return if (arg % 2 == 0) {
        "even"
    } else {
        "odd"
    }
}

//Базовий тест:
fun testIsEven() {
    assertEquals(isEven(1), "odd", "isEven(1)")
    assertEquals(isEven(2), "even", "isEven(2)")
    assertEquals(isEven(34), "even", "isEven(34)")
    assertEquals(isEven(43213102), "even", "isEven(43213102)")
    assertEquals(isEven(0), "even", "isEven(0)")
    assertEquals(isEven(-1), "odd", "isEven(-1)")
    assertEquals(isEven(null), "", "isEven(null)")
}



// 2. Написати функцію, яка починає перебирати числа від 1 до нескінченності і перевіряє чи є число простим (просте число - те яке ділиться лише на 1 чи само на себе). Якщо просте число знайдене - воно додається до списку (можна використати метод add() списку numbers). Після пʼятого знайденого простого числа функція повинна повернути суму всіх пʼяти простих чисел у списку.//
fun sumSimple(): Int {
    val numbers = mutableListOf<Int>()

    fun isPrime(n: Int): Boolean {
        if (n <= 1)
            return false
        for (i in 2 until n) {
            if (n % i == 0)
                return false
        }
        return true
    }

    var i = 1
    while (numbers.size < 5) {
        if (isPrime(i))
            numbers.add(i)
        i++
    }
    return numbers.sum()
}

//Базовий тест
fun testSumSimple() {
    val result = sumSimple()
    val expected = 2 + 3 + 5 + 7 + 11
    assertEquals(result, expected, "sumSimple")
}

// 3. Написати функцію з одним аргументом n, що є числом. Функція повинна повернути суму наступного ряду з n чисел 1 + 11 + 111 + 1111 + ...., де кожний елемент - число з постійно зростаючою кількістю одиниць.
//Наприклад:
//Ви передаєте в функцію аргумент 5.
//Функція рахує сумму для ряду виду: 1 + 11 + 111 + 1111 + 11111.
//В даному прикладі вона повинна повернути число 12345


fun sumOfSeries(n: Int): Int {
    var sum = 0
    var currentTerm = 0
    for (i in 1..n) {
        // Створюємо кожен елемент ряду з n одиниць та додаємо його до загальної суми
        currentTerm = currentTerm * 10 + 1
        sum += currentTerm
    }
    return sum
}

//Базовий тест

fun testSumOfSeries() {
    assertEquals(sumOfSeries(1), 1, "sumOfSeries(1)")
    assertEquals(sumOfSeries(2), 12, "sumOfSeries(2)")
    assertEquals(sumOfSeries(4), 1234, "sumOfSeries(4)")
    assertEquals(sumOfSeries(5), 12345, "sumOfSeries(5)")
    assertEquals(sumOfSeries(7), 1234567, "sumOfSeries(7)")
    assertEquals(sumOfSeries(0), 0, "sumOfSeries(0)")
    assertEquals(sumOfSeries(-1), 0, "sumOfSeries(-1)")
}

    fun main() {
        testSumSimple()
        testSumOfSeries()
        testIsEven()
    }
