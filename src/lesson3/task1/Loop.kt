@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n)
        result *= i
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digitNumber = 0
    var number = n
    do {
        number /= 10
        digitNumber++
    } while (number != 0)
    return digitNumber
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
/**
 *              Coder
 *
 * Small code method but very slow:
 *      if (n < 3) 1 else fib(n - 1) + fib(n - 2)
 *
 * The second method is expensive from memory:
 *      val fibList = arrayListOf(1, 1)
 *      for (i in 3..n)
 *          fibList.add(fibList[i - 2] + fibList[i - 3])
 *      return fibList.last()
 *
 * The last method is easier and faster
 * */
fun fib(n: Int): Int {
    var fib2 = 1
    var fib1 = 1
    var fib = 1
    for (i in 3..n) {
        fib = fib1 + fib2
        fib2 = fib1
        fib1 = fib
    }
    return fib
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int) = abs(m * n) / gcd(m, n)
//    (maxOf(m, n)..(m * n)).first { it % m == 0 && it % n == 0 }

fun gcd(m: Int, n: Int) = (minOf(m, n) downTo 1).first { m % it == 0 && n % it == 0 }

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int) = if (n % 2 == 0) 2 else (3..(n / 3) step 2).firstOrNull { n % it == 0 } ?: n

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int) = ((n / 2) downTo 1).first { n % it == 0 }

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int) =
    if (m % 2 == 0 && n % 2 == 0) false
    else (3..minOf(m, n) step 2).none { m % it == 0 && n % it == 0 }

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n)
        if (sqrt(i.toDouble()) % 1 == 0.0)
            return true
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var y = x
    var counter = 0
    while (y != 1) {
        if (y % 2 == 0) y /= 2
        else y = 3 * y + 1
        counter++
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var sum = 0.0
    val x1 = x % (2 * PI)
    for (n in 0..Int.MAX_VALUE) {
        val ch = (-1.0).pow(n) * x1.pow(2 * n + 1) / factorial(2 * n + 1)
        if (abs(ch) <= eps) break
        sum += ch
    }
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var sum = 0.0
    val x1 = x % (2 * PI)
    for (n in 0..Int.MAX_VALUE) {
        val ch = (-1.0).pow(n) * x1.pow(2 * n) / factorial(2 * n)
        if (abs(ch) <= eps) break
        sum += ch
    }
    return sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var reversedNumber = 0
    do {
        reversedNumber *= 10
        reversedNumber += number % 10
        number /= 10
    } while (number != 0)
    return reversedNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number = n
    var reversedNumber = 0
    do {
        reversedNumber *= 10
        reversedNumber += number % 10
        number /= 10
    } while (number != 0)
    return reversedNumber == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    do {
        if (number % 10 != n % 10)
            return true
        number /= 10
    } while (number != 0)
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var pos = 0
    var q = 0
    for (i in 1..n) {
        q = i * i
        pos += digitNumber(q)
        if (pos >= n) break
    }
    repeat(pos - n) {
        q /= 10
    }
    return q % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var pos = 0
    var f = 0
    for (i in 1..n) {
        f = fib(i)
        pos += digitNumber(f)
        if (pos >= n) break
    }
    repeat(pos - n) {
        f /= 10
    }
    return f % 10
}