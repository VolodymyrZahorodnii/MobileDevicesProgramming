abstract class Employee(
        var name: String,
        var surname: String,
        var baseSalary: Int,
        var experience: Int
) {
    open val countedSalary: Double
        get() {
            val baseSalary = baseSalary.toDouble()
            return when {
                experience > 5 -> baseSalary * 1.2 + 500.0
                experience > 2 -> baseSalary + 200.0
                else -> baseSalary
            }
        }
}

class Developer(
        name: String,
        surname: String,
        baseSalary: Int,
        experience: Int
) : Employee(name, surname, baseSalary, experience) {
}

class Designer(
        name: String,
        surname: String,
        baseSalary: Int,
        experience: Int,
        effCoef: Double
) : Employee(name, surname, baseSalary, experience) {
    var effCoef = checkCoef(effCoef)
        set(value) {
            field = checkCoef(value)
        }

    override val countedSalary: Double
        get() {
            val base = super.countedSalary
            return base * effCoef
        }

    private fun checkCoef(coef: Double): Double {
        return when {
            coef < 0.0 -> 0.0
            coef > 1.0 -> 1.0
            else -> coef
        }
    }
}

class Manager(
        name: String,
        surname: String,
        baseSalary: Int,
        experience: Int,
        val team: MutableList<Employee> = mutableListOf()
) : Employee(name, surname, baseSalary, experience) {
    override val countedSalary: Double
        get() {
            val base = super.countedSalary
            var totalSalary = base

            if (team.size > 10) {
                totalSalary += 300
            } else if (team.size > 5) {
                totalSalary += 200
            }

            val developerCount = team.count { it is Developer }

            if (developerCount > team.size / 2) {
                totalSalary *= 1.1
            }

            return totalSalary
        }
}

class Department(
        val managers: MutableList<Manager> = mutableListOf()
) {
    fun giveSalary() {
        for (manager in managers) {
            println("${manager.name} ${manager.surname} отримав ${manager.countedSalary} шекелів")
            for (employee in manager.team) {
                println("${employee.name} ${employee.surname} отримав ${employee.countedSalary} шекелів")
            }
        }
    }
}

//Переходимо до створення

fun main() {
    // Створення робітників
    val developer1 = Developer("Petro", "Shmetro", 500, 3)
    val developer2 = Developer("Olha", "Petruk", 3200, 6)
    val designer1 = Designer("Maksym", "Bober", 7000, 10, 0.9)
    val designer2 = Designer("Eva", "Watsons", 1600, 2, 1.2)

    // Створення менеджерів та додавання робітників до команд
    val manager1 = Manager("Mike", "Wazowski", 7000, 8)
    manager1.team.add(developer1)
    manager1.team.add(developer2)
    val manager2 = Manager("Pooper", "Shmooper", 7500, 7)
    manager2.team.add(designer1)
    manager2.team.add(designer2)

    // Створення відділу та додавання менеджерів до нього
    val department = Department()
    department.managers.add(manager1)
    department.managers.add(manager2)

    // Розрахунок та виведення зарплат
    department.giveSalary()

}